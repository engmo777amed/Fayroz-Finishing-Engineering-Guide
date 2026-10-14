package com.example.data

import com.example.model.*

/** Hold points for batch 01 stages that have no individually authored legacy checklist. */
object Batch01Checkpoints {
    fun forStage(stage: ExecutionStage): List<QualityCheckItem> = listOf(
        QualityCheckItem(
            id = "check_batch01_${stage.id}_before", categoryId = stage.categoryId, stageId = stage.id,
            titleAr = "قبل البدء: ${stage.titleAr}", phase = InspectionPhase.BEFORE,
            method = "راجع جاهزية الخلفية واعتماد المكونات والخطوة السابقة: ${stage.prerequisites}",
            toolUsed = "المخطط ونشرة الصنف ومحضر استلام الخلفية؛ أداة القياس المحددة للاختبار",
            acceptanceCriterion = "متطلبات البدء محققة ومستندات التنفيذ وحدود القبول محددة قبل صرف الخامة.",
            technicalReference = stage.codeReferences.joinToString("\n")
        ),
        QualityCheckItem(
            id = "check_batch01_${stage.id}_during", categoryId = stage.categoryId, stageId = stage.id,
            titleAr = "قبل التغطية أو الانتقال: ${stage.titleAr}", phase = InspectionPhase.DURING,
            method = "افحص المكونات أو موضع العمل وهو مكشوف؛ تحقق من اشتراطات المرحلة وسجل الملاحظات قبل إخفائها.",
            toolUsed = "شريط قياس أو قدة أو ميزان بحسب المطلوب؛ إجراء فحص مكونات النظام",
            acceptanceCriterion = stage.technicalRequirements.joinToString("\n"),
            technicalReference = stage.codeReferences.joinToString("\n")
        ),
        QualityCheckItem(
            id = "check_guide_${stage.id}", categoryId = stage.categoryId, stageId = stage.id,
            titleAr = "بعد التنفيذ: ${stage.titleAr}", phase = InspectionPhase.AFTER,
            method = stage.qualityTests.joinToString("\n"),
            toolUsed = "أداة وطريقة القياس المحددتان في مواصفة الاستلام، مع التحقق من صلاحية الأداة",
            acceptanceCriterion = stage.handoverCriteria.joinToString("\n"),
            technicalReference = stage.codeReferences.joinToString("\n")
        )
    )
}
