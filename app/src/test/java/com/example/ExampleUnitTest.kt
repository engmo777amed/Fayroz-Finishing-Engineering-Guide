package com.example

import com.example.data.*
import com.example.model.ExecutionStage
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test fun catalogRetainsAllCategoriesAndOriginalIds() {
        assertEquals((1..30).toList(), FinishingCatalog.categories.map { it.id })
        val originalStages = FinishingStagesPart1.stages + FinishingStagesPart2.stages +
            FinishingStagesPart3.stages + FinishingStagesRemaining.stages
        assertTrue(FinishingCatalog.stages.map { it.id }.containsAll(originalStages.map { it.id }))
        assertEquals(96, FinishingCatalog.stages.size)
        assertEquals(131, FinishingCatalog.materials.size)
        assertEquals(FinishingCatalog.stages.size, FinishingCatalog.stages.map { it.id }.distinct().size)
        assertEquals(FinishingCatalog.materials.size, FinishingCatalog.materials.map { it.id }.distinct().size)
    }

    @Test fun everyCategoryHasOrderedPhasesAndAccurateCounts() {
        for (category in FinishingCatalog.categories) {
            val stages = FinishingCatalog.stages.filter { it.categoryId == category.id }
            assertTrue("Category ${category.id} needs multiple phases", stages.size >= 2)
            assertEquals((1..stages.size).toList(), stages.map { it.stageNumber })
            assertEquals(stages.size, category.stagesCount)
            assertEquals(FinishingCatalog.materials.count { category.id in it.categoryIds }, category.materialsCount)
            assertEquals(FinishingDefectsData.defects.count { it.categoryId == category.id }, category.defectsCount)
            assertEquals(FinishingChecklistsData.checkItems.count { it.categoryId == category.id }, category.checklistsCount)
        }
        assertEquals("stage_woodfloor_02", FinishingCatalog.stages.first { it.categoryId == 17 }.id)
        assertEquals("stage_kitchen_02", FinishingCatalog.stages.first { it.categoryId == 24 }.id)
    }

    @Test fun everyPhaseContainsAllGuideSections() {
        for (stage in FinishingCatalog.stages) {
            val textSections = listOf(stage.titleAr, stage.objective, stage.prerequisites,
                stage.dimensionsAndSlopes, stage.mixesAndRatios, stage.mepCoordination)
            val listSections = listOf(stage.requiredMaterials, stage.toolsNeeded, stage.executionSteps,
                stage.technicalRequirements, stage.siteTips, stage.commonMistakes,
                stage.qualityTests, stage.handoverCriteria, stage.codeReferences, stage.relatedMaterialIds)
            assertTrue(stage.id, textSections.all { it.isNotBlank() })
            assertTrue(stage.id, listSections.all { it.isNotEmpty() && it.all(String::isNotBlank) })
        }
    }

    @Test fun materialLinksAreValidAndBidirectional() {
        val stageMap = FinishingCatalog.stages.associateBy { it.id }
        val materialMap = FinishingCatalog.materials.associateBy { it.id }
        for (stage in stageMap.values) for (id in stage.relatedMaterialIds) {
            val material = materialMap[id]
            assertNotNull("${stage.id} -> $id", material)
            assertTrue("$id reverse link", stage.id in material!!.relatedStageIds)
        }
        for (material in materialMap.values) {
            assertTrue(material.id, material.categoryIds.all { it in 1..30 })
            assertTrue(material.id, material.marketNameAr.isNotBlank())
            assertTrue(material.id, material.inspectionAndAcceptance.isNotEmpty())
            for (id in material.relatedStageIds) {
                assertNotNull("${material.id} -> $id", stageMap[id])
                assertTrue(material.id, material.id in stageMap.getValue(id).relatedMaterialIds)
            }
        }
        for (check in FinishingChecklistsData.checkItems) assertNotNull(check.id, stageMap[check.stageId])
        for (defect in FinishingDefectsData.defects) assertNotNull(defect.id, stageMap[defect.stageId])
    }

    @Test fun existingStageInstructionsRemainAvailable() {
        val original = FinishingStagesPart1.stages + FinishingStagesPart2.stages +
            FinishingStagesPart3.stages + FinishingStagesRemaining.stages
        for (stage in original) {
            val enriched = FinishingCatalog.stages.single { it.id == stage.id }
            assertEquals(stage.titleAr, enriched.titleAr)
            assertEquals(stage.executionSteps, enriched.executionSteps)
            assertEquals(stage.objective, enriched.objective)
            assertTrue(enriched.prerequisites.contains(stage.prerequisites))
            assertTrue(enriched.qualityTests.containsAll(stage.qualityTests))
        }
    }
}
