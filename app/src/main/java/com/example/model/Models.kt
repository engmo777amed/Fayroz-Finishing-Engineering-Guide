package com.example.model

enum class InspectionPhase(val titleAr: String) {
    BEFORE("قبل التنفيذ (تجهيزات واستلامات سابقة)"),
    DURING("أثناء التنفيذ (متابعة المصنعية والأعمال المخفية)"),
    AFTER("بعد التنفيذ (الاستواء والتشغيل والنهو)")
}

enum class InspectionStatus(val titleAr: String) {
    PASSED("مطابق ✓"),
    FAILED("غير مطابق ✗"),
    PENDING("لم يُفحص ⏳"),
    NOT_APPLICABLE("لا ينطبق –")
}

enum class DefectSeverity(val titleAr: String) {
    CRITICAL("حرج - أوقف العمل وحدد المعالجة بتقييم مختص"),
    MAJOR("رئيسي - يتطلب معالجة هندسية متخصصة"),
    MODERATE("متوسط - معالجة موضعية لأصل العيب"),
    MINOR("بسيط - معالجة ظاهرية موضعية")
}

enum class DimensionRefType(val titleAr: String) {
    EGYPTIAN_CODE("إلزامي طبقاً للكود المصري"),
    MANUFACTURER_SPEC("توصية الشركة المصنعة المعتمدة"),
    BEST_PRACTICE("ممارسة تنفيذية شائعة ومجربة بالموقع")
}

data class FinishingCategory(
    val id: Int,
    val code: String,
    val titleAr: String,
    val titleEn: String,
    val iconName: String,
    val summaryAr: String,
    val stagesCount: Int,
    val materialsCount: Int,
    val defectsCount: Int,
    val checklistsCount: Int
)

data class ExecutionStage(
    val id: String,
    val categoryId: Int,
    val stageNumber: Int,
    val titleAr: String,
    val objective: String,
    val prerequisites: String,
    val requiredMaterials: List<String>,
    val toolsNeeded: List<String>,
    val executionSteps: List<String>,
    val dimensionsAndSlopes: String,
    val mixesAndRatios: String,
    val technicalRequirements: List<String>,
    val mepCoordination: String,
    val siteTips: List<String>,
    val commonMistakes: List<String>,
    val qualityTests: List<String>,
    val handoverCriteria: List<String>,
    val codeReferences: List<String>,
    val relatedMaterialIds: List<String> = emptyList()
)

data class MaterialCard(
    val id: String,
    val categoryIds: List<Int>,
    val marketNameAr: String,
    val technicalNameAr: String,
    val tradeNames: List<String>,
    val classification: String,
    val definitionAndFunction: String,
    val componentsAndProperties: String,
    val availableTypes: List<String>,
    val dimensionsAndSpecs: String,
    val qualityGrades: String,
    val standardSpecs: String,
    val egyptianBrands: List<String>,
    val usages: List<String>,
    val prosAndCons: String,
    val installationMethod: String,
    val installationTools: List<String>,
    val storageAndHandling: String,
    val inspectionAndAcceptance: List<String>,
    val counterfeitDetection: String,
    val commonMisuses: List<String>,
    val lifespan: String,
    val maintenanceInstructions: String,
    val relatedStageIds: List<String> = emptyList()
)

data class QualityCheckItem(
    val id: String,
    val categoryId: Int,
    val stageId: String,
    val titleAr: String,
    val phase: InspectionPhase,
    val method: String,
    val toolUsed: String,
    val acceptanceCriterion: String,
    val technicalReference: String
)

data class TroubleshootingDefect(
    val id: String,
    val categoryId: Int,
    val stageId: String,
    val titleAr: String,
    val marketName: String,
    val description: String,
    val detectionSigns: List<String>,
    val probableCauses: List<String>,
    val rootCauseDiagnosis: String,
    val impactOnWork: String,
    val severity: DefectSeverity,
    val repairSteps: List<String>,
    val repairMaterials: List<String>,
    val preventionMethod: String,
    val handoverRetest: String
)

data class TechnicalDimension(
    val id: String,
    val categoryId: Int,
    val itemTitle: String,
    val standardValue: String,
    val tolerance: String,
    val applicationCondition: String,
    val referenceType: DimensionRefType,
    val referenceSource: String,
    val practicalNotes: String
)

data class InspectionCheckResult(
    val checkItemId: String,
    val checkItemTitle: String,
    val phaseTitle: String,
    val toolUsed: String,
    val criterion: String,
    val status: InspectionStatus = InspectionStatus.PENDING,
    val notes: String = ""
)
