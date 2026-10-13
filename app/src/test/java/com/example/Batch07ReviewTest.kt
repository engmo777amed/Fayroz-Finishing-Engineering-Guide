package com.example

import com.example.data.Batch07References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch07ReviewTest {
    @Test fun tileStagesFollowApprovalInstallationSpecialRoutesAndHandover() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 15 }
        assertEquals(listOf("stage_review_70", "stage_tiles_02", "stage_flooring_01", "stage_review_71", "stage_review_72", "stage_tiles_03"), stages.map { it.id })
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
    }

    @Test fun stoneStagesFollowSelectionShopDrawingSpecialRouteAndHandover() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 16 }
        assertEquals(listOf("stage_review_73", "stage_review_75", "stage_review_74", "stage_marble_01", "stage_marble_02", "stage_marble_03"), stages.map { it.id })
        assertEquals((1..6).toList(), stages.map { it.stageNumber })
    }

    @Test fun floorStagesSeparateSubstrateClickParquetLvtAndHandover() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 17 }
        assertEquals(listOf("stage_woodfloor_02", "stage_woodfloor_01", "stage_review_76", "stage_review_77", "stage_woodfloor_03"), stages.map { it.id })
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in 15..17 }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun universalSiteRecipesWereRemoved() {
        val text = FinishingCatalog.stages.filter { it.categoryId in 15..17 }.joinToString(" ") {
            listOf(it.prerequisites, it.dimensionsAndSlopes, it.mixesAndRatios, it.technicalRequirements.joinToString(" ")).joinToString(" ")
        }
        assertFalse(text.contains("10-15 يوم"))
        assertFalse(text.contains("أقل من 4%"))
        assertFalse(text.contains("قدة 3م"))
        assertFalse(text.contains("1مم"))
    }

    @Test fun misplacedMaterialsAreRemovedAndFloorProductsAreSeparated() {
        assertFalse(16 in FinishingCatalog.materials.single { it.id == "mat_anti_glare_downlights" }.categoryIds)
        assertFalse(17 in FinishingCatalog.materials.single { it.id == "mat_drywall_screws_and_hangers" }.categoryIds)
        assertFalse(17 in FinishingCatalog.materials.single { it.id == "mat_shadowline_profile_w_angle" }.categoryIds)
        assertFalse(17 in FinishingCatalog.materials.single { it.id == "mat_drywall_inspection_access_panel" }.categoryIds)
        val flooring = FinishingCatalog.materials.single { it.id == "mat_hdf_flooring_ac4" }
        assertTrue(flooring.availableTypes.any { it.contains("SPC") })
        assertTrue(flooring.classification.contains("لا تجمع تعليماتهما"))
    }

    @Test fun referencesStartWithProjectDocumentsAndLimitExamples() {
        (15..17).forEach { categoryId ->
            val references = Batch07References.forCategory(categoryId)
            assertTrue(references.first().startsWith("مستندات المشروع"))
            assertTrue(references.drop(1).all { it.contains("لا تعمم") || it.contains("لا يغني") })
        }
    }

    @Test fun practicalGuidesExposeAlternativeRoutes() {
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_15_selection" }.body.contains("مسارات"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_16_selection" }.body.contains("افصل"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_17_selection" }.body.contains("LVT"))
    }

    @Test fun figuresDeclareEducationalLimitsAndStoneSupportIsNotBuildableDetail() {
        TechnicalIllustrations.figures.filter { it.categoryIds.any { id -> id in 15..17 } }.forEach {
            assertTrue(it.id, it.limits.contains("صورة تعليمية"))
        }
        val stone = TechnicalIllustrations.figures.single { it.id == "stone_counter" }
        assertTrue(stone.caption.contains("ليسَا تفصيلة تثبيت قابلة للتنفيذ"))
    }

    @Test fun defectsDiagnoseCauseBeforeRepair() {
        val tile = FinishingDefectsData.defects.single { it.id == "def_ceramic_hollow" }
        val floor = FinishingDefectsData.defects.single { it.id == "def_hdf_buckling_cupping" }
        assertTrue(tile.repairSteps.first().contains("حدد"))
        assertTrue(floor.rootCauseDiagnosis.contains("الرطوبة"))
    }
}
