package com.example

import com.example.data.FinishingCatalog
import org.junit.Assert.*
import org.junit.Test

class FinalIntegrationTest {
    private fun stage(id: String) = FinishingCatalog.stages.single { it.id == id }

    @Test fun plumbingAndWaterproofingShareDrainDetailAndRetestBeforeCovering() {
        assertTrue(stage("stage_waterproofing_01").prerequisites.contains("اختبر الخدمات"))
        assertTrue(stage("stage_waterproofing_01").mepCoordination.contains("فلنشة"))
        assertTrue(stage("stage_plumbing_02").executionSteps.last().contains("بالعزل"))
        assertTrue(stage("stage_plumbing_02").executionSteps.last().contains("قبل"))
        assertTrue(stage("stage_waterproofing_04").executionSteps.any { it.contains("أعد الاختبار قبل الإغلاق") })
    }

    @Test fun gypsumClosureRequiresTestedElectricalServicesAndMaintenanceAccess() {
        val closure = stage("stage_gypsum_02")
        assertTrue(closure.prerequisites.contains("اختبار الخدمات المخفية"))
        assertTrue(closure.mepCoordination.contains("الكهرباء"))
        assertTrue(closure.mepCoordination.contains("قبل إغلاق"))
        assertTrue(closure.executionSteps.last().contains("للصيانة"))
        assertTrue(stage("stage_electric_02").executionSteps.last().contains("علبة متاحة"))
        assertTrue(stage("stage_electric_02").qualityTests.any { it.contains("فصل الأجهزة الحساسة") })
    }

    @Test fun facadeAndWindowPreserveDrainageAcrossTheirSharedOpening() {
        val window = stage("stage_aluminum_01")
        assertTrue(stage("stage_facade_01").prerequisites.contains("الشبابيك"))
        assertTrue(stage("stage_facade_03").executionSteps.any { it.contains("اتصال العزل بالإطار") })
        assertTrue(window.executionSteps.any { it.contains("فتحات التصريف دون انسداد") })
        assertTrue(window.executionSteps.last().contains("حد القبول"))
        assertTrue(stage("stage_facade_03").executionSteps.any { it.contains("بعد المعالجة") })
    }

    @Test fun floorsAndDoorsShareFinalLevelAndPreserveFloatingMovement() {
        assertTrue(stage("stage_flooring_01").prerequisites.contains("الأبواب"))
        assertTrue(stage("stage_carpentry_01").prerequisites.contains("منسوب الأرضية النهائي"))
        assertTrue(stage("stage_woodfloor_03").executionSteps.any { it.contains("الحلق") })
        assertTrue(stage("stage_woodfloor_03").executionSteps.any { it.contains("ولا تثبتها في اللوح العائم") })
        assertTrue(stage("stage_woodfloor_03").executionSteps.any { it.contains("فتح الأبواب") })
        assertTrue(stage("stage_carpentry_01").dimensionsAndSlopes.contains("شهادة النظام"))
    }
}
