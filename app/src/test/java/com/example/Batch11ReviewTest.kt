package com.example

import com.example.data.Batch11References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.data.FinishingDimensionsData
import com.example.data.PracticalGuideData
import com.example.data.TechnicalIllustrations
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch11ReviewTest {
    @Test fun facadeStagesStartWithApprovalThenSeparateAlternativeRoutes() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 23 }
        assertEquals(
            listOf("stage_facade_01", "stage_review_93", "stage_review_94", "stage_review_95", "stage_review_96", "stage_facade_02", "stage_review_97", "stage_review_98", "stage_facade_03"),
            stages.map { it.id }
        )
        assertEquals((1..9).toList(), stages.map { it.stageNumber })
        assertTrue(stages.first().titleAr.startsWith("اعتماد نظام"))
        stages.slice(1..7).forEach { assertTrue(it.id, it.titleAr.startsWith("مسار بديل")) }
    }

    @Test fun metalStagesFollowSelectionFabricationDesignInstallationAndTest() {
        val stages = FinishingCatalog.stages.filter { it.categoryId == 26 }
        assertEquals(listOf("stage_review_105", "stage_metal_02", "stage_review_104", "stage_metal_01", "stage_metal_03"), stages.map { it.id })
        assertEquals((1..5).toList(), stages.map { it.stageNumber })
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in setOf(23, 26) }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun universalFacadeAndRailingRecipesWereRemoved() {
        val text = (FinishingCatalog.stages.filter { it.categoryId in setOf(23, 26) } +
            FinishingCatalog.materials.filter { 23 in it.categoryIds || 26 in it.categoryIds }).joinToString(" ")
        listOf("أنكر بولت صلب هيلتي مقاس 10-12 مم", "سمك 1.5 مم", "غير ممغنط تماماً", "لا يصدأ أو يتقشر إطلاقاً مدى الحياة", "ثبات مطلق").forEach {
            assertFalse(it, text.contains(it))
        }
    }

    @Test fun gypsumFinishingMaterialIsNotAnExteriorFacadeMaterial() {
        val gypsum = FinishingCatalog.materials.single { it.id == "mat_mussiees_gypsum_super" }
        assertFalse(23 in gypsum.categoryIds)
    }

    @Test fun referencesStartWithProjectDocumentsAndLimitExamples() {
        listOf(23, 26).forEach { categoryId ->
            val references = Batch11References.forCategory(categoryId)
            assertTrue(references.first().startsWith("مستندات المشروع"))
            assertTrue(references.drop(1).all { it.contains("لا ") })
        }
    }

    @Test fun guidesExplainAlternativesAndStopConditions() {
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_23_selection" }.body.contains("هذه بدائل"))
        assertTrue(PracticalGuideData.entries.single { it.id == "guide_26_selection" }.body.contains("المغناطيس"))
        listOf(23, 26).forEach { categoryId ->
            assertTrue(PracticalGuideData.entries.single { it.id == "guide_${categoryId}_inspection" }.body.contains("توقف"))
        }
    }

    @Test fun noUniversalDimensionAndFiguresKeepEducationalLimits() {
        assertTrue(FinishingDimensionsData.dimensions.none { it.categoryId in setOf(23, 26) })
        TechnicalIllustrations.figures.filter { 23 in it.categoryIds || 26 in it.categoryIds }.forEach {
            assertTrue(it.id, it.limits.contains("صورة تعليمية"))
            assertTrue(it.id, it.limits.contains("لا يُستخرج"))
        }
    }

    @Test fun defectsDiagnoseFacadeLeakAndLooseRailingSafely() {
        val facade = FinishingDefectsData.defects.single { it.id == "def_additional_23" }
        val railing = FinishingDefectsData.defects.single { it.id == "def_additional_26" }
        assertTrue(facade.rootCauseDiagnosis.contains("اختبار"))
        assertTrue(railing.rootCauseDiagnosis.contains("امنع استخدام"))
    }
}
