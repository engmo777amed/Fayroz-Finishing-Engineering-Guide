package com.example

import com.example.data.*
import org.junit.Assert.*
import org.junit.Test

/** Guard the technical contradictions identified in the approved content review. */
class TechnicalContentRegressionTest {
    @Test fun materialPropertiesAreDistinctAndRelevant() {
        val cards = FinishingCatalog.materials.filter { it.id.startsWith("mat_review_") }
        assertEquals(116, cards.size)
        for (card in cards) {
            assertNotEquals(card.id, card.definitionAndFunction, card.componentsAndProperties)
            assertNotEquals(card.id, card.componentsAndProperties, card.dimensionsAndSpecs)
            assertFalse(card.id, card.counterfeitDetection.contains("المغناطيس"))
            assertFalse(card.id, card.classification.contains("خامة أو مكون"))
        }
    }

    @Test fun eachDefectHasItsOwnDiagnosisAndRetest() {
        val defects = FinishingDefectsData.defects
        assertEquals(15, defects.size)
        assertEquals(defects.size, defects.map { it.rootCauseDiagnosis }.distinct().size)
        assertEquals(defects.size, defects.map { it.handoverRetest }.distinct().size)
        val ppr = defects.single { it.id == "def_plumbing_joint_leak" }
        assertFalse(ppr.probableCauses.joinToString().contains("النحاس"))
        assertTrue(ppr.rootCauseDiagnosis.contains("الحرارة"))
        val wood = defects.single { it.id == "def_hdf_buckling_cupping" }
        assertTrue(wood.repairSteps.any { it.contains("المتضرر") })
    }

    @Test fun individualInspectionMethodsSurviveCatalogAssembly() {
        for (check in FinishingChecklistsData.checkItems) {
            val actual = FinishingCatalog.checklistItems.single { it.id == check.id }
            assertEquals(check.id, check.method, actual.method)
            assertEquals(check.id, check.acceptanceCriterion, actual.acceptanceCriterion)
            assertEquals(check.id, check.toolUsed, actual.toolUsed)
        }
        val sameStage = FinishingCatalog.checklistItems.filter { it.stageId == "stage_masonry_01" }
        assertEquals(sameStage.size, sameStage.map { it.method }.distinct().size)
    }

    @Test fun criticalProceduresNoLongerUseUniversalLimits() {
        fun stage(id: String) = FinishingCatalog.stages.single { it.id == id }
        val ppr = stage("stage_plumbing_01")
        assertTrue(ppr.executionSteps.any { it.contains("زمن التبريد") })
        assertFalse(ppr.executionSteps.any { it.contains("6 ثوان") })
        val data = stage("stage_lowcurrent_01")
        assertTrue(data.qualityTests.any { it.contains("أداء") })
        val hvac = stage("stage_hvac_01")
        assertFalse(hvac.executionSteps.any { it.contains("25 بار") })
        val rail = stage("stage_metal_01")
        assertFalse(rail.qualityTests.any { it.contains("100 كجم") })
        val door = FinishingDimensionsData.dimensions.single { it.id == "dim_door_rough_opening" }
        assertTrue(door.practicalNotes.contains("حلق"))
        assertTrue(door.applicationCondition.contains("التشطيب النهائي"))
    }
}
