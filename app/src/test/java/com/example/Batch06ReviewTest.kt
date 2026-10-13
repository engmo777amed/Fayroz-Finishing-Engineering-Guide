package com.example

import com.example.data.Batch06References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch06ReviewTest {
    @Test fun lowCurrentStagesFollowPlanningInstallationTestingOrder() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 12 }
        assertEquals(
            listOf("stage_review_63", "stage_review_64", "stage_lowcurrent_01", "stage_lowcurrent_02", "stage_smart_home_01"),
            stages.map { it.id }
        )
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
    }

    @Test fun fireStagesFollowHiddenWorksDevicesIntegrationCommissioningOrder() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 14 }
        assertEquals(
            listOf("stage_fire_02", "stage_fire_01", "stage_review_68", "stage_review_69", "stage_fire_03"),
            stages.map { it.id }
        )
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
    }

    @Test fun reviewedStagesHaveBeforeDuringAfterInspection() {
        listOf("stage_lowcurrent_02", "stage_smart_home_01", "stage_fire_01", "stage_fire_03").forEach { stageId ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stageId }.map { it.phase }.toSet()
            assertTrue(stageId, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun networkAcceptanceDoesNotPromiseUniversalGigabit() {
        val text = FinishingCatalog.stages.filter { it.categoryId == 12 }
            .joinToString(" ") { it.handoverCriteria.joinToString(" ") + " " + it.dimensionsAndSlopes }
        assertFalse(text.contains("1000 Mbps على كافة المخارج"))
        assertTrue(text.contains("Wiremap") || text.contains("الاختبار"))
    }

    @Test fun fireContentDoesNotKeepUniversalPressureTemperatureOrCoverageRecipe() {
        val text = FinishingCatalog.stages.filter { it.categoryId == 14 }
            .joinToString(" ") { it.prerequisites + " " + it.dimensionsAndSlopes + " " + it.handoverCriteria.joinToString(" ") }
        assertFalse(text.contains("14 بار"))
        assertFalse(text.contains("68°C"))
        assertFalse(text.contains("12-16 م2"))
    }

    @Test fun sprinklerCardIsNotMixedWithSmokeDetectors() {
        val card = FinishingCatalog.materials.single { it.id == "mat_fire_sprinkler_pendant" }
        assertEquals("رشاش حريق أوتوماتيكي", card.marketNameAr)
        assertFalse(card.technicalNameAr.contains("Detector"))
        assertTrue(FinishingCatalog.materials.single { it.id == "mat_fire_alarm_devices" }.availableTypes.contains("كاشف دخان"))
    }

    @Test fun genericSealantAndConcealedCisternAreNotFireMaterials() {
        assertFalse(14 in FinishingCatalog.materials.single { it.id == "mat_polyurethane_joint_and_crack_sealant" }.categoryIds)
        assertFalse(FinishingCatalog.materials.any { "concealed_cistern" in it.id && 14 in it.categoryIds })
    }

    @Test fun referencesSeparateProjectDocumentsFromExternalExamples() {
        listOf(12, 14).forEach { categoryId ->
            val references = Batch06References.forCategory(categoryId)
            assertTrue(references.first().startsWith("مستندات المشروع"))
            assertTrue(references.drop(1).all { it.startsWith("Fluke") || it.startsWith("ONVIF") || it.startsWith("Tyco") || it.startsWith("Honeywell") || it.startsWith("Hilti") })
        }
    }

    @Test fun fireDoesNotReuseElectricalPanelIllustration() {
        val electrical = TechnicalIllustrations.figures.single { it.id == "electrical_components" }
        assertFalse(14 in electrical.categoryIds)
    }

    @Test fun practicalGuidesExposeStopConditionsAndTestLimits() {
        val lowCurrent = PracticalGuideData.entries.single { it.id == "guide_12_inspection" }.body
        val fire = PracticalGuideData.entries.single { it.id == "guide_14_inspection" }.body
        assertTrue(lowCurrent.contains("متى تتوقف"))
        assertTrue(lowCurrent.contains("Wiremap"))
        assertTrue(fire.contains("متى تتوقف"))
        assertTrue(fire.contains("نقطة معزولة"))
    }

    @Test fun defectsRequireCauseIsolationAndFinalNormalState() {
        val poe = FinishingDefectsData.defects.single { it.id == "def_additional_12" }
        val fire = FinishingDefectsData.defects.single { it.id == "def_additional_14" }
        assertTrue(poe.probableCauses.size >= 4)
        assertTrue(poe.rootCauseDiagnosis.contains("Wiremap"))
        assertTrue(fire.handoverRetest.contains("الحالة الطبيعية"))
    }
}
