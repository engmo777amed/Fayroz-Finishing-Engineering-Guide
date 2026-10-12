package com.example

import com.example.data.Batch03References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch03ReviewTest {
    @Test fun plumbingOrderRunsFromCoordinationToFinalDocumentedTests() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 7 }
        val ids = stages.map { it.id }
        assertEquals(25, ids.size)
        assertEquals("stage_review_6", ids.first())
        assertTrue(ids.indexOf("stage_review_9") < ids.indexOf("stage_plumbing_01"))
        assertTrue(ids.indexOf("stage_plumbing_01") < ids.indexOf("stage_plumbing_02"))
        assertEquals("stage_plumbing_03", ids.last())
        assertEquals((1..25).toList(), stages.map { it.stageNumber })
    }

    @Test fun supplyStageDoesNotPresentOldUniversalNumbers() {
        val stage = FinishingCatalog.stages.single { it.id == "stage_plumbing_01" }
        val text = listOf(stage.objective, stage.siteTips.joinToString(), stage.handoverCriteria.joinToString(), stage.codeReferences.joinToString()).joinToString(" ")
        assertFalse(text.contains("260"))
        assertFalse(text.contains("15 سم"))
        assertFalse(text.contains("+1.00"))
        assertTrue(stage.codeReferences.any { it.contains("مستندات المشروع") })
        assertTrue(Batch03References.forCategory(7).any { it.contains("مرجع نطاق نظام") })
    }

    @Test fun plumbingDimensionsAreProjectOrFixtureSpecific() {
        val dimensions = FinishingDimensionsData.dimensions.filter { it.categoryId == 7 }
        assertEquals(3, dimensions.size)
        val text = dimensions.joinToString(" ") { "${it.standardValue} ${it.practicalNotes} ${it.referenceSource}" }
        assertFalse(text.contains("15 سم بين"))
        assertFalse(text.contains("10–12 سم"))
        assertTrue(text.contains("المخطط") || text.contains("الموديل") || text.contains("الأجهزة"))
    }

    @Test fun concealedPlumbingHasBeforeDuringAndAfterCheckpoints() {
        val phases = FinishingCatalog.checklistItems
            .filter { it.stageId == "stage_plumbing_01" }
            .map { it.phase }.toSet()
        assertTrue(phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
    }

    @Test fun plumbingDefectSeparatesLeakRestrictionAndWaterHammer() {
        val defect = FinishingDefectsData.defects.single { it.id == "def_plumbing_joint_leak" }
        val text = listOf(defect.titleAr, defect.description, defect.probableCauses.joinToString(), defect.repairSteps.joinToString()).joinToString(" ")
        assertTrue(text.contains("تسريب"))
        assertTrue(text.contains("ضعف") || text.contains("تضيق"))
        assertTrue(text.contains("مطرقة") || text.contains("طرق"))
    }
}
