package com.example

import com.example.data.Batch04References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch04ReviewTest {
    @Test fun sanitaryStagesFollowActualExecutionOrder() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 8 }
        assertEquals(
            listOf("stage_review_28", "stage_sanitary_01", "stage_review_30", "stage_review_29",
                "stage_sanitary_02", "stage_sanitary_03", "stage_review_31"),
            stages.map { it.id }
        )
        assertEquals((1..7).toList(), stages.map { it.stageNumber })
    }

    @Test fun hvacSeparatesPlanningInstallationTestingAndDiagnosis() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 13 }
        assertEquals(
            listOf("stage_review_65", "stage_hvac_01", "stage_hvac_02", "stage_hvac_03",
                "stage_review_66", "stage_review_67"),
            stages.map { it.id }
        )
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
    }

    @Test fun unsupportedUniversalNumbersWereRemovedFromReviewedRecords() {
        val sanitary = FinishingCatalog.stages.filter { it.categoryId == 8 }.joinToString(" ")
        assertFalse(sanitary.contains("400 كجم"))
        assertFalse(sanitary.contains("EN 997"))
        val hvacDimension = FinishingDimensionsData.dimensions.single { it.id == "dim_ac_roughin_heights" }
        assertTrue(hvacDimension.tolerance.contains("لا يعمم خلوص 15 سم"))
        val defect = FinishingDefectsData.defects.single { it.id == "def_ac_drain_overflow" }
        val defectText = "${defect.description} ${defect.rootCauseDiagnosis} ${defect.repairSteps}"
        assertFalse(defectText.contains("1 سم/متر"))
        assertFalse(defectText.contains("1.5%"))
        assertFalse(defectText.contains("1 بوصة"))
    }

    @Test fun sanitaryAndHvacStagesHaveThreePhaseInspection() {
        listOf("stage_sanitary_01", "stage_hvac_01", "stage_hvac_03").forEach { stageId ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stageId }.map { it.phase }.toSet()
            assertTrue(stageId, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun referencesDistinguishProjectDocumentsFromProductExamples() {
        val sanitary = Batch04References.forCategory(8)
        val hvac = Batch04References.forCategory(13)
        assertTrue(sanitary.first().contains("مستندات المشروع"))
        assertTrue(sanitary.drop(1).all { it.contains("مثال") || it.contains("خاص") })
        assertTrue(hvac.first().contains("مستندات المشروع"))
        assertTrue(hvac.drop(1).all { it.contains("موديل محدد") })
    }
}
