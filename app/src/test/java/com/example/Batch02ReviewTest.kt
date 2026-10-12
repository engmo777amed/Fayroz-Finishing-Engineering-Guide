package com.example

import com.example.data.*
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch02ReviewTest {
    @Test fun systemSelectionAndHiddenDetailsPrecedeApplication() {
        val ids = FinishingCatalog.stages.filter { it.categoryId == 4 }.map { it.id }
        assertTrue(ids.indexOf("stage_review_43") < ids.indexOf("stage_waterproofing_01"))
        assertTrue(ids.indexOf("stage_review_47") < ids.indexOf("stage_waterproofing_02"))
        assertTrue(ids.indexOf("stage_waterproofing_03") < ids.indexOf("stage_waterproofing_04"))
        val roof = FinishingCatalog.stages.filter { it.categoryId == 6 }.map { it.id }
        assertTrue(roof.indexOf("stage_review_52") < roof.indexOf("stage_screed_02"))
        assertTrue(FinishingCatalog.checklistItems.any { it.stageId == "stage_review_47" && it.phase == InspectionPhase.DURING })
    }
    @Test fun numbersBelongToProductsAndNotEveryRoof() {
        val sheet = FinishingCatalog.materials.single { it.id == "mat_bitumen_membrane_4mm" }
        assertTrue(sheet.dimensionsAndSpecs.contains("SikaShield P24"))
        assertTrue(sheet.dimensionsAndSpecs.contains("100"))
        assertTrue(sheet.dimensionsAndSpecs.contains("150"))
        val cement = FinishingCatalog.materials.single { it.id == "mat_cement_waterproofing_2k" }
        assertTrue(cement.dimensionsAndSpecs.contains("107 EL"))
        assertTrue(cement.dimensionsAndSpecs.contains("هذا الصنف فقط"))
        val joint = FinishingCatalog.materials.single { it.id == "mat_sikaflex_polyurethane_sealant" }
        assertFalse(joint.usages.any { it.contains("كل 4 إلى 6") })
        assertFalse(joint.counterfeitDetection.contains("40%"))
    }
    @Test fun positionalEditsFollowOnlyAnUnambiguousUnchangedPoint() {
        val old = listOf("أ", "ب", "ج")
        assertEquals(1, ContentReviewEditMigration.targetIndex(old, listOf("ج", "أ", "ب"), 0))
        assertNull(ContentReviewEditMigration.targetIndex(old, listOf("ج", "أ المعدلة", "ب"), 0))
        assertNull(ContentReviewEditMigration.targetIndex(listOf("أ", "أ"), listOf("أ"), 0))
        assertNull(ContentReviewEditMigration.targetIndex(old, listOf("أ", "أ"), 0))
    }
}
