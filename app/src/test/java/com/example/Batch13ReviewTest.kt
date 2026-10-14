package com.example

import com.example.data.Batch13References
import com.example.data.FinishingCatalog
import com.example.data.FinishingDefectsData
import com.example.model.InspectionPhase
import org.junit.Assert.*
import org.junit.Test

class Batch13ReviewTest {
    @Test fun qualityAndHandoverFollowExecutionOrder() {
        assertEquals(
            listOf("stage_review_109", "stage_review_110", "stage_qa_tests_01", "stage_qa_tests_02", "stage_qa_tests_03"),
            FinishingCatalog.stages.filter { it.categoryId == 29 }.map { it.id }
        )
        assertEquals(
            listOf("stage_snagging_01", "stage_snagging_02", "stage_review_111", "stage_snagging_03", "stage_review_112"),
            FinishingCatalog.stages.filter { it.categoryId == 30 }.map { it.id }
        )
    }

    @Test fun everyReviewedStageHasBeforeDuringAfterInspection() {
        FinishingCatalog.stages.filter { it.categoryId in setOf(29, 30) }.forEach { stage ->
            val phases = FinishingCatalog.checklistItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
            assertTrue(stage.id, phases.containsAll(setOf(InspectionPhase.BEFORE, InspectionPhase.DURING, InspectionPhase.AFTER)))
        }
    }

    @Test fun universalTestNumbersAndAbsolutePromisesWereRemoved() {
        val text = (FinishingCatalog.stages.filter { it.categoryId in setOf(29, 30) } +
            FinishingCatalog.materials.filter { 29 in it.categoryIds || 30 in it.categoryIds }).joinToString(" ")
        listOf("500V/1000V", "قدة ألومنيوم 3م", "إتمام الـ 48 ساعة", "بنسبة < 10%", "خالية من أي عيوب مستترة").forEach {
            assertFalse(it, text.contains(it))
        }
        assertTrue(text.contains("حد القبول"))
        assertTrue(text.contains("إعادة"))
    }

    @Test fun referencesSeparateProjectDocumentsFromExamples() {
        listOf(29, 30).forEach { categoryId ->
            val refs = Batch13References.forCategory(categoryId)
            assertTrue(refs.first().startsWith("مستندات المشروع"))
            assertTrue(refs.drop(1).all { it.contains("لا ") })
        }
    }

    @Test fun defectsRequireDiagnosisAndDocumentedRetest() {
        val test = FinishingDefectsData.defects.single { it.id == "def_additional_29" }
        val handover = FinishingDefectsData.defects.single { it.id == "def_additional_30" }
        assertTrue(test.rootCauseDiagnosis.contains("معاير"))
        assertTrue(test.handoverRetest.contains("القراءات"))
        assertTrue(handover.rootCauseDiagnosis.contains("السبب"))
        assertTrue(handover.handoverRetest.contains("توثيق"))
    }

    @Test fun inspectionToolsExplainTheirLimits() {
        val tools = FinishingCatalog.materials.single { it.id == "mat_snagging_inspection_tools" }
        assertEquals(listOf(30), tools.categoryIds)
        assertTrue(tools.definitionAndFunction.contains("لا تستبدل"))
        assertTrue(tools.commonMisuses.any { it.contains("فاحص البريزة") })
    }
}
