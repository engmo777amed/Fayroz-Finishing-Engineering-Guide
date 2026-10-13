package com.example

import com.example.data.Batch08References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch08ReviewTest {
    @Test fun paintStagesFollowApprovalPreparationApplicationAndAlternativeRoutes() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 18 }
        assertEquals(
            listOf("stage_review_78", "stage_paint_01", "stage_review_79", "stage_review_80", "stage_paint_02", "stage_review_81", "stage_paint_03", "stage_review_82", "stage_review_83"),
            stages.map { it.id }
        )
        assertEquals((1..9).toList(), stages.map { it.stageNumber })
        assertTrue(stages.filter { it.id in setOf("stage_review_81", "stage_paint_03", "stage_review_82", "stage_review_83") }
            .all { it.titleAr.startsWith("مسار بديل") })
    }

    @Test fun gypsumStagesKeepHiddenWorksInspectionBeforeClosing() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 19 }
        assertEquals(listOf("stage_review_85", "stage_gypsum_01", "stage_gypsum_02", "stage_gypsum_03", "stage_review_84"), stages.map { it.id })
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
        val boarding = stages.single { it.id == "stage_gypsum_02" }
        assertTrue(boarding.prerequisites.contains("قبل الغلق"))
        assertTrue(boarding.executionSteps.first().contains("الخدمات"))
        assertTrue(stages.last().titleAr.startsWith("مسار بديل"))
    }

    @Test fun decorStagesSeparateHeavyAndLightRoutes() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 20 }
        assertEquals(listOf("stage_review_86", "stage_decor_03", "stage_decor_02", "stage_decor_01"), stages.map { it.id })
        assertEquals((1..4).toList(), stages.map { it.stageNumber })
        assertTrue(stages.single { it.id == "stage_decor_02" }.titleAr.contains("الجبس"))
        assertTrue(stages.single { it.id == "stage_decor_01" }.titleAr.contains("البوليمر"))
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in 18..20 }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun universalPaintAndDrywallRecipesWereRemoved() {
        val text = FinishingCatalog.stages.filter { it.categoryId in 18..20 }.joinToString(" ") {
            listOf(it.prerequisites, it.dimensionsAndSlopes, it.mixesAndRatios, it.executionSteps.joinToString(" ")).joinToString(" ")
        }
        listOf("أقل من 10%", "21 يوم", "0.6 مم", "50-60 سم", "كل 70", "بعد 24 ساعة", "45 درجة حادة").forEach {
            assertFalse(it, text.contains(it))
        }
    }

    @Test fun categoryMembershipRemovesTileAndFloorPollution() {
        listOf("mat_tile_expansion_joints_and_t_profiles", "mat_epoxy_tile_grout", "mat_polyester_marble_mastic_jolly", "mat_tile_leveling_clips_and_wedges", "mat_epoxy_self_leveling_flooring")
            .forEach { id ->
                val categories = FinishingCatalog.materials.single { it.id == id }.categoryIds
                assertFalse(id, categories.any { it in 18..20 })
            }
        assertEquals(listOf(19), FinishingCatalog.materials.single { it.id == "mat_drywall_screws_and_hangers" }.categoryIds)
        assertEquals(listOf(19), FinishingCatalog.materials.single { it.id == "mat_shadowline_profile_w_angle" }.categoryIds)
        assertFalse(20 in FinishingCatalog.materials.single { it.id == "mat_gypsum_board_knauf" }.categoryIds)
        assertFalse(19 in FinishingCatalog.materials.single { it.id == "mat_fiber_mesh_160" }.categoryIds)
        assertFalse(19 in FinishingCatalog.materials.single { it.id == "mat_fire_sprinkler_pendant" }.categoryIds)
    }

    @Test fun referencesStartWithProjectDocumentsAndLimitExamples() {
        (18..20).forEach { categoryId ->
            val references = Batch08References.forCategory(categoryId)
            assertTrue(references.first().startsWith("مستندات المشروع"))
            assertTrue(references.drop(1).all { it.contains("لا تعمم") || it.contains("لا يعمم") })
        }
    }

    @Test fun dimensionsAreSystemSpecificAndNotDerivedFromFigures() {
        val grid = FinishingDimensionsData.dimensions.single { it.id == "dim_gypsum_grid_spacing" }
        val pelmet = FinishingDimensionsData.dimensions.single { it.id == "dim_curtain_pelmet_box" }
        assertTrue(grid.standardValue.contains("رقم نظام"))
        assertFalse(grid.applicationCondition.contains("12.5"))
        assertTrue(pelmet.practicalNotes.contains("لا تستخرج المقاس من صورة"))
    }

    @Test fun practicalGuidesExposeRoutesAndStopConditions() {
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_18_selection" }.body.contains("المعدن"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_19_selection" }.body.contains("افصل"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_20_selection" }.body.contains("الجبس"))
        (18..20).forEach { categoryId ->
            assertTrue(PracticalGuideData.entries.single { it.id == "guide_${categoryId}_inspection" }.body.contains("توقف"))
        }
    }

    @Test fun figuresAreRelevantAndDeclareEducationalLimits() {
        assertFalse(18 in TechnicalIllustrations.figures.single { it.id == "wood_floor" }.categoryIds)
        assertFalse(20 in TechnicalIllustrations.figures.single { it.id == "door" }.categoryIds)
        TechnicalIllustrations.figures.filter { it.categoryIds.any { id -> id in 18..20 } }.forEach {
            assertTrue(it.id, it.limits.contains("صورة تعليمية"))
        }
    }

    @Test fun defectsDiagnoseBeforeRepairAndAvoidUniversalNumbers() {
        val paint = FinishingDefectsData.defects.single { it.id == "def_paint_peeling" }
        val gypsum = FinishingDefectsData.defects.single { it.id == "def_gypsum_joint_cracking" }
        val decor = FinishingDefectsData.defects.single { it.id == "def_additional_20" }
        assertTrue(paint.rootCauseDiagnosis.contains("حدد طبقة الانفصال"))
        assertFalse(paint.description.contains("30-50"))
        assertTrue(gypsum.repairSteps.first().contains("الحركة"))
        assertTrue(decor.rootCauseDiagnosis.contains("أمّن القطعة"))
    }
}
