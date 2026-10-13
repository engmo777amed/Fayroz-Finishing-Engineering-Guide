package com.example

import com.example.data.Batch10References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch10ReviewTest {
    @Test fun windowStagesFollowSelectionGlassThresholdThenInstallation() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 22 }
        assertEquals(listOf("stage_review_90", "stage_review_91", "stage_review_92", "stage_aluminum_01", "stage_aluminum_02", "stage_aluminum_03"), stages.map { it.id })
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
        assertTrue(stages.single { it.id == "stage_aluminum_01" }.titleAr.startsWith("مسار الألومنيوم"))
    }

    @Test fun shutterStagesSeparateManualMotorAndCurtainRoutes() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 25 }
        assertEquals(listOf("stage_review_102", "stage_shutter_02", "stage_shutter_03", "stage_review_103", "stage_shutter_01"), stages.map { it.id })
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
        assertTrue(stages.single { it.id == "stage_shutter_02" }.titleAr.startsWith("مسار بديل"))
        assertTrue(stages.single { it.id == "stage_shutter_03" }.titleAr.startsWith("مسار بديل"))
        assertTrue(stages.single { it.id == "stage_review_103" }.titleAr.startsWith("مسار مستقل"))
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in setOf(22, 25) }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun universalWindowAndShutterRecipesWereRemoved() {
        val text = (FinishingCatalog.stages.filter { it.categoryId in setOf(22, 25) } +
            FinishingCatalog.materials.filter { 22 in it.categoryIds || 25 in it.categoryIds }).joinToString(" ")
        listOf("كل 40 سم", "الخلوص (1 سم", "لمدة 15 دقيقة", "لمدة 10 دقائق", "20 سم وعمق 15 سم", "بنسبة 85%", "بنسبة 70%").forEach {
            assertFalse(it, text.contains(it))
        }
    }

    @Test fun unrelatedShowerHardwareIsNotAWindowMaterial() {
        val shower = FinishingCatalog.materials.single { it.id == "mat_shower_glass_hardware_10mm" }
        assertFalse(22 in shower.categoryIds)
    }

    @Test fun referencesStartWithProjectDocumentsAndLimitExamples() {
        listOf(22, 25).forEach { categoryId ->
            val references = Batch10References.forCategory(categoryId)
            assertTrue(references.first().startsWith("مستندات المشروع"))
            assertTrue(references.drop(1).all { it.contains("لا يحدد") || it.contains("لا تحول") || it.contains("لا تعمم") || it.contains("لا يثبت") })
        }
    }

    @Test fun guidesExplainAlternativesTermsAndStopConditions() {
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_22_selection" }.body.contains("مساران بديلان"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_25_selection" }.body.contains("مسارات مختلفة"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_25_terms" }.body.contains("ليس هو صندوق الشتر"))
        listOf(22, 25).forEach { categoryId ->
            assertTrue(PracticalGuideData.entries.single { it.id == "guide_${categoryId}_inspection" }.body.contains("توقف"))
        }
    }

    @Test fun noUniversalDimensionOrUnrelatedIllustrationIsPresented() {
        assertTrue(FinishingDimensionsData.dimensions.none { it.categoryId in setOf(22, 25) })
        assertTrue(TechnicalIllustrations.figures.none { figure -> figure.categoryIds.any { it in setOf(22, 25) } })
    }

    @Test fun defectsDiagnoseAndDoNotMandateUniversalSillDrop() {
        val window = FinishingDefectsData.defects.single { it.id == "def_aluminum_window_water_leak" }
        val shutter = FinishingDefectsData.defects.single { it.id == "def_additional_25" }
        assertTrue(window.rootCauseDiagnosis.contains("اختبار متدرج"))
        assertFalse(window.preventionMethod.contains("2 سم"))
        assertTrue(shutter.rootCauseDiagnosis.contains("فصل حراري"))
    }
}
