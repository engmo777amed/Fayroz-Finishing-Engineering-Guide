package com.example

import com.example.data.*
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch01ReviewTest {
    @Test fun preparationAndCornersPrecedePlasterApplication() {
        val prep = FinishingCatalog.stages.filter { it.categoryId == 2 }.map { it.id }
        assertTrue(prep.indexOf("stage_review_38") < prep.indexOf("stage_prep_02"))
        assertTrue(prep.indexOf("stage_review_39") < prep.indexOf("stage_prep_03"))
        val plaster = FinishingCatalog.stages.filter { it.categoryId == 3 }.map { it.id }
        assertTrue(plaster.indexOf("stage_review_41") < plaster.indexOf("stage_plaster_01"))
        assertTrue(plaster.indexOf("stage_plaster_05") < plaster.indexOf("stage_plaster_03"))
        assertTrue(plaster.indexOf("stage_review_42") < plaster.indexOf("stage_plaster_08"))
    }

    @Test fun hiddenMasonryAndPreparationReceiveEarlierHoldPoints() {
        val stage = FinishingCatalog.stages.single { it.id == "stage_review_36" }
        val checks = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }
        assertTrue(checks.any { it.phase == InspectionPhase.BEFORE })
        assertTrue(checks.any { it.phase == InspectionPhase.DURING })
        assertTrue(checks.any { it.phase == InspectionPhase.AFTER })
        assertFalse(checks.any { it.toolUsed == stage.toolsNeeded.joinToString(" • ") })
    }

    @Test fun headMovementAndProductMixingAreNotUniversalRecipes() {
        val masonry = FinishingCatalog.stages.single { it.id == "stage_masonry_02" }
        assertFalse(masonry.handoverCriteria.any { it.contains("التشحيط محكم ومضغوط") })
        val separation = FinishingDefectsData.defects.single { it.id == "def_masonry_column_separation" }
        assertTrue(separation.preventionMethod.contains("لا تستخدم الحشر"))
        assertFalse(separation.preventionMethod.contains("إلزامياً"))
        val plaster = FinishingCatalog.materials.single { it.id == "mat_drymix_plaster" }
        assertTrue(plaster.dimensionsAndSpecs.contains("DRY MORTAR 300"))
        assertTrue(plaster.dimensionsAndSpecs.contains("لهذا الصنف فقط"))
        assertFalse(plaster.prosAndCons.contains("خالية من الشروخ"))
    }
}
