package com.example

import com.example.data.Batch12References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch12ReviewTest {
    @Test fun jointStagesFollowApprovalThenAlternativeRoutesAndRepair() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 27 }
        assertEquals(
            listOf("stage_adhesives_01", "stage_review_106", "stage_adhesives_02", "stage_adhesives_03", "stage_review_107"),
            stages.map { it.id }
        )
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
        assertTrue(stages.first().titleAr.startsWith("اعتماد"))
        assertTrue(stages[1].titleAr.startsWith("مسار بديل"))
        assertTrue(stages.last().titleAr.contains("سبب الفشل"))
    }

    @Test fun finalTrimsFollowMeasureGlassAccessoriesAndProtection() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 28 }
        assertEquals(
            listOf("stage_finaltrims_01", "stage_finaltrims_02", "stage_review_108", "stage_finaltrims_03"),
            stages.map { it.id }
        )
        assertEquals((1..4).toList(), stages.map { it.stageNumber })
        assertTrue(stages.first().titleAr.contains("قياس"))
        assertTrue(stages[1].titleAr.contains("زجاج الأمان"))
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in setOf(27, 28) }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun dangerousUniversalRecipesAndPromisesWereRemoved() {
        val text = (FinishingCatalog.stages.filter { it.categoryId in setOf(27, 28) } +
            FinishingCatalog.materials.filter { 27 in it.categoryIds || 28 in it.categoryIds }).joinToString(" ")
        listOf(
            "عدم خروج نقطة ماء واحدة",
            "عزل تام لمياه الشاور",
            "غير قابل للمغنطة ولا يصدأ",
            "عتبة رخام سمك 2 سم",
            "زجاج سيكوريت مقسى 10 مم شفاف"
        ).forEach { assertFalse(it, text.contains(it)) }
        assertTrue(text.contains("لا يوجد سمك 10 مم"))
    }

    @Test fun unrelatedMaterialsAreNotFiledAsAdhesivesOrFinalTrims() {
        listOf("mat_clean_mix_water", "mat_cement_waterproofing_2k", "mat_hdf_flooring_ac4").forEach {
            assertFalse(it, 27 in FinishingCatalog.materials.single { material -> material.id == it }.categoryIds)
        }
        listOf("mat_acrylic_putty", "mat_polyurethane_cornice", "mat_aluminum_jumbo_profiles", "mat_stainless_handrail_304").forEach {
            assertFalse(it, 28 in FinishingCatalog.materials.single { material -> material.id == it }.categoryIds)
        }
    }

    @Test fun referencesStartWithProjectDocumentsAndLimitProductExamples() {
        listOf(27, 28).forEach { categoryId ->
            val refs = Batch12References.forCategory(categoryId)
            assertTrue(refs.first().startsWith("مستندات المشروع"))
            assertTrue(refs.drop(1).all { it.contains("لا ") })
        }
    }

    @Test fun guidesSeparateFunctionAndStopUnsafeGlassWork() {
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_27_selection" }.body.contains("ابدأ بالوظيفة"))
        val glass = PracticalGuideData.entries.single { it.id == "guide_28_inspection" }.body
        assertTrue(glass.contains("توقف"))
        assertTrue(glass.contains("ثقب زجاج مقسى"))
    }

    @Test fun figuresAndDimensionsDoNotPretendToBeApprovedDetails() {
        assertTrue(FinishingDimensionsData.dimensions.none { it.categoryId in setOf(27, 28) })
        TechnicalIllustrations.figures.filter { 27 in it.categoryIds || 28 in it.categoryIds }.forEach {
            assertTrue(it.id, it.limits.contains("صورة تعليمية"))
            assertTrue(it.id, it.limits.contains("لا يُستخرج"))
        }
    }

    @Test fun defectsDiagnoseCompatibilityAndWaterPathBeforeRepair() {
        val joint = FinishingDefectsData.defects.single { it.id == "def_additional_27" }
        val shower = FinishingDefectsData.defects.single { it.id == "def_additional_28" }
        assertTrue(joint.rootCauseDiagnosis.contains("عينة"))
        assertTrue(shower.rootCauseDiagnosis.contains("ميل"))
        assertTrue(shower.rootCauseDiagnosis.contains("عزل"))
    }
}
