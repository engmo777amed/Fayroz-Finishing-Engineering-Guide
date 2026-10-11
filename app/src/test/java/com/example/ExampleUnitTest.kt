package com.example

import com.example.data.FinishingCategoriesData
import com.example.data.FinishingDimensionsData
import com.example.data.FinishingMaterialsPart1
import com.example.data.FinishingMaterialsPart2
import com.example.data.FinishingStagesPart1
import com.example.data.FinishingStagesPart2
import com.example.data.FinishingStagesPart3
import com.example.data.FinishingStagesRemaining
import com.example.util.FinishingCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testAllThirtyFinishingCategoriesExist() {
        val categories = FinishingCategoriesData.categories
        assertEquals("Must have 30 categories", 30, categories.size)
        // Verify contiguous IDs from 1 to 30
        for (i in 1..30) {
            val cat = categories.find { it.id == i }
            assertNotNull("Category $i must exist", cat)
            assertTrue("Category title must not be blank", cat!!.titleAr.isNotBlank())
            assertTrue("Category code must start with SEC-", cat.code.startsWith("SEC-"))
        }
    }

    @Test
    fun testEveryCategoryHasExecutionStages() {
        val allStages = FinishingStagesPart1.stages + FinishingStagesPart2.stages + FinishingStagesPart3.stages + FinishingStagesRemaining.stages
        for (i in 1..30) {
            val stagesForCat = allStages.filter { s -> s.categoryId == i }
            assertTrue("Category $i must have execution stages", stagesForCat.isNotEmpty())
        }
    }

    @Test
    fun testPlasterCalculatorOutputs() {
        val res = FinishingCalculator.calculatePlaster(areaM2 = 100.0, thicknessCm = 2.0)
        assertTrue("Cement bags must be positive", res.cementBags50kg > 0)
        assertTrue("Sand volume must be positive", res.sandM3 > 0.0)
        assertTrue("Addibond must be positive", res.addibondKg > 0.0)
    }

    @Test
    fun testTilesCalculatorOutputs() {
        val resPorcelain = FinishingCalculator.calculateTiles(netAreaM2 = 100.0, wastePercent = 7.0, isPorcelain = true)
        assertEquals("Gross area must match 107.0 m2", 107.0, resPorcelain.grossAreaM2, 0.01)
        assertTrue("Porcelain adhesive bags must be > 10", resPorcelain.adhesiveBags25kg >= 10)

        val resCeramic = FinishingCalculator.calculateTiles(netAreaM2 = 100.0, wastePercent = 7.0, isPorcelain = false)
        assertTrue("Ceramic bedding cement bags must be > 20", resCeramic.cementBags50kg >= 20)
    }

    @Test
    fun testGypsumCalculatorOutputs() {
        val res = FinishingCalculator.calculateGypsum(areaM2 = 50.0)
        assertTrue("Gypsum boards count must be > 15", res.boardsCount >= 15)
        assertTrue("Omega profiles must be > 25", res.omegaProfiles >= 25)
    }

    @Test
    fun testMasonryCalculatorOutputs() {
        val resRed = FinishingCalculator.calculateMasonry(areaM2 = 20.0, isRedBrick = true)
        assertTrue("Red bricks count for 20m2 should be ~1160", resRed.blocksCount >= 1000)

        val resBlock = FinishingCalculator.calculateMasonry(areaM2 = 20.0, isRedBrick = false)
        assertTrue("Cement blocks count for 20m2 should be ~270", resBlock.blocksCount in 250..300)
    }

    @Test
    fun testEgyptianMaterialsDataQuality() {
        val allMaterials = FinishingMaterialsPart1.materials + FinishingMaterialsPart2.materials
        assertTrue("Materials library must have items", allMaterials.isNotEmpty())
        for (mat in allMaterials) {
            assertTrue("Market name must not be blank", mat.marketNameAr.isNotBlank())
            assertTrue("Must contain Egyptian brands", mat.egyptianBrands.isNotEmpty())
            assertTrue("Must contain counterfeit detection instructions", mat.counterfeitDetection.isNotBlank())
            assertTrue("Must contain inspection criteria", mat.inspectionAndAcceptance.isNotEmpty())
        }
    }

    @Test
    fun testStandardsAndDimensionsData() {
        val dimensions = FinishingDimensionsData.dimensions
        assertTrue("Dimensions database must have items", dimensions.isNotEmpty())
        val sherb = dimensions.find { it.id == "dim_sherb_level" }
        assertNotNull("Sherb level must exist", sherb)
        assertEquals("+1.00 متر (100 سم)", sherb?.standardValue)
    }
}
