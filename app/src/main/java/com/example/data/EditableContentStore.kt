package com.example.data

import android.content.Context
import java.security.MessageDigest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EditableField(val key: String, val label: String, val original: String)
data class EditableRecord(val scope: String, val kind: String, val title: String, val fields: List<EditableField>)

/** User text is separate from the bundled catalog and keyed by stable record/field IDs. */
class EditableContentStore(context: Context) {
    private val preferences = context.applicationContext.getSharedPreferences("fayroz_text_edits_v1", Context.MODE_PRIVATE)
    init {
        // Final release migrates from the delivered v1.4 catalog. Intermediate CI APKs are not releases.
        if (!preferences.getBoolean("content_review_v14_lists_migrated", false)) {
            val groups = org.json.JSONArray(context.assets.open("content_review_edit_migration.json").bufferedReader().use { it.readText() })
            val editor = preferences.edit()
            val writes = linkedMapOf<String, String>()
            for (g in 0 until groups.length()) {
                val group = groups.getJSONObject(g)
                val scope = group.getString("scope")
                val old = group.getJSONArray("old").let { a -> List(a.length()) { a.getString(it) } }
                val revised = group.getJSONArray("new").let { a -> List(a.length()) { a.getString(it) } }
                for (index in old.indices) {
                    val key = "$scope|$index"
                    if (!preferences.contains(key)) continue
                    val edit = preferences.getString(key, null) ?: continue
                    editor.remove(key)
                    val target = ContentReviewEditMigration.targetIndex(old, revised, index)
                    if (target != null) writes["$scope|$target"] = edit
                    else {
                        // Keep ambiguous edits available in the text editor, alongside their old context.
                        writes["archived|$key"] = edit
                        writes["archive_context|$key"] = old[index]
                    }
                }
            }
            writes.forEach { (key, value) -> editor.putString(key, value) }
            editor.putBoolean("content_review_v14_lists_migrated", true).commit()
        }
    }
    private val _revision = MutableStateFlow(0)
    val revision = _revision.asStateFlow()
    val records: List<EditableRecord> by lazy {
        buildList {
            preferences.all.keys.filter { it.startsWith("archived|") }.sorted().forEach { key ->
                val oldKey = key.removePrefix("archived|")
                val contextText = preferences.getString("archive_context|$oldKey", oldKey) ?: oldKey
                add(EditableRecord(key, "تعديلات محفوظة قبل المراجعة", contextText,
                    listOf(EditableField(key, "تعديلك السابق — راجع سياقه قبل نقله", contextText))))
            }
            FinishingCatalog.categories.forEach { add(record("category", it.id.toString(), "الأقسام", it.titleAr, it)) }
            FinishingCatalog.stages.forEach { add(record("stage", it.id, "المراحل", it.titleAr, it)) }
            FinishingCatalog.materials.forEach { add(record("material", it.id, "الخامات", it.marketNameAr, it)) }
            FinishingDefectsData.defects.forEach { add(record("defect", it.id, "المشاكل والحلول", it.titleAr, it)) }
            FinishingDimensionsData.dimensions.forEach { add(record("dimension", it.id, "المقاسات", it.itemTitle, it)) }
            FinishingCatalog.checklistItems.forEach { add(record("check", it.id, "قوائم الاستلام", it.titleAr, it)) }
            PracticalGuideData.entries.forEach { add(record("guide", it.id, "الدليل العملي", it.title, it)) }
            TechnicalIllustrations.figures.forEach { add(record("figure", it.id, "وصف الصور", it.title, it)) }
        }
    }
    private fun record(kind: String, id: String, label: String, title: String, value: Any): EditableRecord {
        val scope = "$kind|$id"
        val fields = value.javaClass.declaredFields.flatMap { field ->
            if (field.name in excludedFields || field.isSynthetic) return@flatMap emptyList()
            field.isAccessible = true
            when (val raw = field.get(value)) {
                is String -> listOf(EditableField("$scope|${field.name}", fieldLabels[field.name] ?: field.name, raw))
                is List<*> -> raw.mapIndexedNotNull { index, item ->
                    (item as? String)?.let { EditableField("$scope|${field.name}|$index", "${fieldLabels[field.name] ?: field.name} — ${index + 1}", it) }
                }
                else -> emptyList()
            }
        }
        return EditableRecord(scope, label, title, fields)
    }
    fun value(key: String, original: String): String = preferences.getString(key, original) ?: original
    fun isEdited(key: String): Boolean = preferences.contains(key)
    fun save(key: String, value: String): Boolean {
        if (key.isBlank() || value.isBlank()) return false
        val success = preferences.edit().putString(key, value).commit()
        if (success) _revision.value++
        return success
    }
    fun reset(key: String): Boolean {
        val success = preferences.edit().remove(key).commit()
        if (success) _revision.value++
        return success
    }
    fun text(kind: String, id: String, field: String, original: String) = value("$kind|$id|$field", original)
    fun texts(kind: String, id: String, field: String, original: List<String>) = original.mapIndexed { i, s -> value("$kind|$id|$field|$i", s) }
    private val fieldsByKey by lazy { records.flatMap { it.fields }.associateBy { it.key } }
    fun field(key: String): EditableField? = fieldsByKey[key]
    private fun normalized(s: String) = s.trim().removePrefix("✓ ").removePrefix("⚠️ ").removePrefix("📜 ").replace(Regex("^[•·\\u0007]\\s*"), "").replace(Regex("^\\d+[.)]\\s*"), "").trim()
    private var lookupRevision = -1
    private var fieldLookup: Map<String, List<EditableField>> = emptyMap()
    fun target(text: String, scope: String, uiKey: String): EditableField {
        val needle = normalized(text)
        fun matches(field: EditableField) = normalized(field.original) == needle || normalized(value(field.key, field.original)) == needle
        val scoped = records.filter { it.scope == scope }.flatMap { it.fields }
        if (lookupRevision != _revision.value) {
            fieldLookup = records.flatMap { it.fields }.flatMap { field ->
                listOf(normalized(field.original), normalized(value(field.key, field.original))).distinct().map { it to field }
            }.groupBy({ it.first }, { it.second })
            lookupRevision = _revision.value
        }
        val match = scoped.filter(::matches).singleOrNull() ?: fieldLookup[needle]?.singleOrNull()
        if (match != null) return match
        val identity = if (uiKey.isNotBlank()) uiKey + "|" + MessageDigest.getInstance("SHA-256").digest(text.toByteArray()).take(8).joinToString("") { "%02x".format(it) } else MessageDigest.getInstance("SHA-256").digest(text.toByteArray()).joinToString("") { "%02x".format(it) }
        return EditableField("ui|$scope|$identity", "النص المعروض", text)
    }
    companion object {
        private val excludedFields = setOf("id", "scope", "kind", "categoryId", "categoryIds", "relatedStageIds", "relatedMaterialIds", "stageId", "stageNumber", "iconName", "code", "assetName", "referenceType", "severity", "phase")
        private val fieldLabels = mapOf(
            "titleAr" to "العنوان", "title" to "العنوان", "titleEn" to "الاسم الإنجليزي", "summaryAr" to "ملخص القسم",
            "objective" to "الهدف", "prerequisites" to "قبل التنفيذ", "requiredMaterials" to "الخامات المطلوبة", "toolsNeeded" to "الأدوات",
            "executionSteps" to "خطوات التنفيذ", "dimensionsAndSlopes" to "المقاسات والميول", "mixesAndRatios" to "الخلط والجرعات",
            "technicalRequirements" to "اشتراطات التنفيذ", "mepCoordination" to "تنسيق الخدمات", "siteTips" to "نصائح الموقع", "commonMistakes" to "أخطاء التنفيذ",
            "qualityTests" to "الاختبارات", "handoverCriteria" to "حدود القبول", "codeReferences" to "المراجع", "marketNameAr" to "اسم السوق",
            "technicalNameAr" to "الاسم الفني", "tradeNames" to "أسماء بديلة", "classification" to "التصنيف", "definitionAndFunction" to "التعريف والوظيفة",
            "componentsAndProperties" to "المكونات والخصائص", "availableTypes" to "الأنواع", "dimensionsAndSpecs" to "المواصفات", "qualityGrades" to "درجات الجودة",
            "standardSpecs" to "المواصفة والمرجع", "egyptianBrands" to "أمثلة الماركات", "usages" to "الاستخدامات", "prosAndCons" to "المميزات والقيود",
            "installationMethod" to "طريقة التركيب", "installationTools" to "أدوات التركيب", "storageAndHandling" to "التشوين", "inspectionAndAcceptance" to "الفحص والقبول",
            "counterfeitDetection" to "فحص المطابقة والأصالة", "commonMisuses" to "الاستخدام الخاطئ", "lifespan" to "العمر والخدمة", "maintenanceInstructions" to "الصيانة",
            "marketName" to "الاسم المتداول", "description" to "الوصف", "detectionSigns" to "علامات العيب", "probableCauses" to "الأسباب",
            "rootCauseDiagnosis" to "التشخيص", "impactOnWork" to "التأثير", "repairSteps" to "خطوات الإصلاح", "repairMaterials" to "خامات الإصلاح",
            "preventionMethod" to "الوقاية", "handoverRetest" to "إعادة الفحص", "itemTitle" to "البند", "standardValue" to "القيمة والشرط", "tolerance" to "التفاوت",
            "applicationCondition" to "شروط التطبيق", "referenceSource" to "المصدر", "practicalNotes" to "ملاحظات التنفيذ", "method" to "طريقة الفحص",
            "toolUsed" to "أداة الفحص", "acceptanceCriterion" to "معيار القبول", "technicalReference" to "المرجع الفني", "body" to "الشرح", "caption" to "شرح الصورة",
            "sourceUrl" to "رابط مرجع الصورة", "dimensionText" to "الأبعاد وشروطها", "labels" to "الأجزاء الظاهرة", "limits" to "حدود التطبيق", "source" to "المصدر"
        )
    }
}
