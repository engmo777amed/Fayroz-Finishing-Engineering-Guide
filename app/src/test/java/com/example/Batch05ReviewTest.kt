package com.example

import com.example.data.Batch05References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch05ReviewTest {
    @Test fun electricalStagesFollowExecutionOrder() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 9 }
        assertEquals(
            listOf("stage_review_55", "stage_review_56", "stage_electric_01",
                "stage_electric_02", "stage_review_57", "stage_electric_03"),
            stages.map { it.id }
        )
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
    }

    @Test fun panelAndLightingSeparatePlanningInstallationAndTesting() {
        assertEquals(
            listOf("stage_review_58", "stage_review_59", "stage_review_60",
                "stage_panel_01", "stage_panel_02", "stage_panel_03"),
            FinishingCatalog.stages.filter { it.categoryId == 10 }.map { it.id }
        )
        assertEquals(
            listOf("stage_review_62", "stage_lighting_01", "stage_lighting_02",
                "stage_lighting_03", "stage_review_61"),
            FinishingCatalog.stages.filter { it.categoryId == 11 }.map { it.id }
        )
    }

    @Test fun universalSocketHeightsAndBreakerRecipesWereRemoved() {
        val dimensions = FinishingDimensionsData.dimensions.filter { it.categoryId == 9 }
            .joinToString(" ") { "${it.standardValue} ${it.applicationCondition} ${it.practicalNotes}" }
        assertFalse(dimensions.contains("15-20 سم"))
        assertFalse(dimensions.contains("90 سم + 25 سم"))
        assertFalse(dimensions.contains("10A للإنارة"))
        assertTrue(dimensions.contains("ليست وصفة عامة"))
    }

    @Test fun reviewedStagesHaveBeforeDuringAfterInspection() {
        listOf("stage_electric_01", "stage_panel_01", "stage_lighting_02").forEach { stageId ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stageId }.map { it.phase }.toSet()
            assertTrue(stageId, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun referencesSeparateProjectDocumentsFromManufacturerExamples() {
        (9..11).forEach { categoryId ->
            val refs = Batch05References.forCategory(categoryId)
            assertTrue(refs.first().startsWith("مستندات المشروع"))
            assertTrue(refs.drop(1).all { it.contains("مرجع") || it.contains("مثال") })
        }
    }

    @Test fun residualCurrentDefectIsDiagnosticNotSingleCause() {
        val defect = FinishingDefectsData.defects.single { it.id == "def_rccb_nuisance_tripping" }
        assertFalse(defect.description.contains("30mA"))
        assertTrue(defect.probableCauses.size >= 4)
        assertTrue(defect.repairSteps.first().contains("اعزل"))
        assertTrue(defect.handoverRetest.contains("تيار وزمن فصل"))
    }
}
