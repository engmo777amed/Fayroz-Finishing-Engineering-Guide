package com.example

import com.example.data.Batch09References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch09ReviewTest {
    @Test fun doorStagesFollowApprovalFrameAlternativesLeafAndFinish() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 21 }
        assertEquals(listOf("stage_review_87", "stage_carpentry_01", "stage_review_88", "stage_review_89", "stage_carpentry_02", "stage_carpentry_03"), stages.map { it.id })
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
        assertTrue(stages.single { it.id == "stage_review_88" }.titleAr.startsWith("مسار بديل"))
        assertTrue(stages.single { it.id == "stage_review_89" }.titleAr.startsWith("مسار بديل"))
    }

    @Test fun cabinetryStagesStartWithCoordinationAndSeparateFixedFurniture() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 24 }
        assertEquals(listOf("stage_kitchen_01", "stage_review_99", "stage_kitchen_02", "stage_review_100", "stage_kitchen_03", "stage_review_101"), stages.map { it.id })
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
        assertTrue(stages.first().titleAr.contains("قبل التصنيع"))
        assertTrue(stages.last().titleAr.startsWith("مسار مستقل"))
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in setOf(21, 24) }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun universalDoorAndKitchenRecipesWereRemovedFromCoreCards() {
        val stages = FinishingCatalog.stages.filter { it.categoryId in setOf(21, 24) }.joinToString(" ")
        val cards = FinishingCatalog.materials.filter { it.id in setOf("mat_wooden_subframe_doors", "mat_stainless_ball_bearing_hinges", "mat_door_cylinder_locks_and_handles", "mat_kitchen_hpl_counter") }.joinToString(" ")
        listOf("10-12 سم", "6 كانات", "80 كجم", "200,000", "205-207", "180°C", "93%", "2-3 مم").forEach {
            assertFalse(it, stages.contains(it) || cards.contains(it))
        }
    }

    @Test fun coreMaterialsUseOnlyRelevantCategoryMemberships() {
        assertEquals(listOf(21), FinishingCatalog.materials.single { it.id == "mat_wooden_subframe_doors" }.categoryIds)
        assertEquals(listOf(21), FinishingCatalog.materials.single { it.id == "mat_stainless_ball_bearing_hinges" }.categoryIds)
        assertEquals(listOf(21), FinishingCatalog.materials.single { it.id == "mat_door_cylinder_locks_and_handles" }.categoryIds)
        assertEquals(listOf(24), FinishingCatalog.materials.single { it.id == "mat_kitchen_hpl_counter" }.categoryIds)
    }

    @Test fun referencesStartWithProjectDocumentsAndLimitManufacturerExamples() {
        listOf(21, 24).forEach { categoryId ->
            val references = Batch09References.forCategory(categoryId)
            assertTrue(references.first().startsWith("مستندات المشروع"))
            assertTrue(references.drop(1).all {
                it.contains("لا تعمم") || it.contains("لا يعمم") || it.contains("لا يثبت اعتماد")
            })
        }
    }

    @Test fun kitchenDimensionDependsOnUsersAndActualAppliances() {
        val dimension = FinishingDimensionsData.dimensions.single { it.id == "dim_kitchen_cabinet_heights" }
        assertTrue(dimension.itemTitle.contains("المستخدم"))
        assertTrue(dimension.applicationCondition.contains("اعتماد الأجهزة"))
        assertTrue(dimension.referenceSource.contains("أكواد الأجهزة"))
        assertFalse(dimension.standardValue.contains("90"))
    }

    @Test fun guidesExplainMarketTermsAndStopConditions() {
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_21_terms" }.body.contains("ليس كل الأبواب"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_24_terms" }.body.contains("HPL"))
        listOf(21, 24).forEach { categoryId ->
            assertTrue(PracticalGuideData.entries.single { it.id == "guide_${categoryId}_inspection" }.body.contains("توقف"))
        }
    }

    @Test fun figuresAreRelevantAndDeclareEducationalLimits() {
        val door = TechnicalIllustrations.figures.single { it.id == "door" }
        val cabinet = TechnicalIllustrations.figures.single { it.id == "cabinet" }
        assertTrue(21 in door.categoryIds)
        assertTrue(24 in cabinet.categoryIds)
        listOf(door, cabinet).forEach {
            assertTrue(it.limits.contains("صورة تعليمية"))
            assertTrue(it.limits.contains("لا يُستخرج"))
        }
    }

    @Test fun defectsDiagnoseBeforeRepair() {
        val door = FinishingDefectsData.defects.single { it.id == "def_additional_21" }
        val kitchen = FinishingDefectsData.defects.single { it.id == "def_additional_24" }
        assertTrue(door.rootCauseDiagnosis.contains("قبل قص الضلفة"))
        assertTrue(kitchen.rootCauseDiagnosis.contains("افحص مصادر الماء"))
        assertFalse(kitchen.preventionMethod.contains("السيليكون وحده"))
    }
}
