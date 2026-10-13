package com.example.data

import com.example.model.ExecutionStage
import com.example.model.FinishingCategory
import com.example.model.MaterialCard
import com.example.model.QualityCheckItem
import com.example.model.InspectionPhase

/** Pure, immutable catalog shared by the repository and integrity tests. Original IDs stay stable. */
object FinishingCatalog {
    private val mergedMaterialIds = setOf("mat_corner_bead_galvanized", "mat_ready_mix_plaster", "mat_tile_leveling_clips_and_wedges", "mat_concealed_toilet_cistern_flushing_system")
    fun canonicalMaterialId(id: String): String = materialAliases[id] ?: id

    private val originalStages = FinishingStagesPart1.stages + FinishingStagesPart2.stages +
        FinishingStagesPart3.stages + FinishingStagesRemaining.stages
    private val addedStages = FinishingStagesExpansion1.stages +
        FinishingStagesExpansion2.stages +
        FinishingStagesExpansion3.stages +
        FinishingStagesExpansion4.stages +
        FinishingStagesExpansion5.stages +
        FinishingStagesExpansion6.stages +
        FinishingStagesReview1.stages +
        FinishingStagesReview2.stages +
        FinishingStagesReview3.stages +
        FinishingStagesReview4.stages +
        FinishingStagesReview5.stages +
        FinishingStagesReview6.stages
    private val unmergedMaterials = FinishingMaterialsPart1.materials +
        FinishingMaterialsPart2.materials +
        FinishingMaterialsPart3.materials +
        FinishingMaterialsPart4.materials +
        FinishingMaterialsPart5.materials +
        FinishingMaterialsPart6.materials +
        FinishingMaterialsPart7.materials +
        FinishingMaterialsPart8.materials +
        FinishingMaterialsPart9.materials +
        FinishingMaterialsPart10.materials +
        FinishingMaterialsExpansion1.materials +
        FinishingMaterialsExpansion2.materials +
        FinishingMaterialsExpansion3.materials +
        FinishingMaterialsExpansion4.materials +
        FinishingMaterialsExpansion5.materials + FinishingMaterialsExpansion6.materials +
        FinishingMaterialsReview1.materials +
        FinishingMaterialsReview2.materials +
        FinishingMaterialsReview3.materials +
        FinishingMaterialsReview4.materials +
        FinishingMaterialsReview5.materials +
        FinishingMaterialsReview6.materials +
        FinishingMaterialsReview7.materials

    private val rawMaterials = unmergedMaterials.filter { it.id !in mergedMaterialIds }

    private val aliases = mapOf(
        "stage_facades_01" to "stage_facade_01",
        "stage_wood_floor_01" to "stage_woodfloor_01",
        "stage_decorative_01" to "stage_decor_01",
        "stage_shutters_01" to "stage_shutter_01",
        "stage_metals_01" to "stage_metal_01",
        "stage_final_trims_01" to "stage_finaltrims_01",
        "stage_doors_01" to "stage_carpentry_01",
        "stage_doors_02" to "stage_carpentry_02",
        "stage_alum_01" to "stage_aluminum_01",
        "stage_alum_02" to "stage_aluminum_02",
        "stage_tiles_01" to "stage_flooring_01",
        "stage_ceramic_01" to "stage_flooring_01",
        "stage_ceramic_02" to "stage_tiles_02",
        "stage_plumb_01" to "stage_plumbing_01",
        "stage_plumb_02" to "stage_plumbing_02",
        "stage_elec_01" to "stage_electric_01",
        "stage_elec_02" to "stage_electric_02"
    )

    private val materialAliases = mapOf(
        "mat_corner_bead_galvanized" to "mat_corner_bead_galv",
        "mat_ready_mix_plaster" to "mat_drymix_plaster",
        "mat_tile_leveling_clips_and_wedges" to "mat_tile_leveling_clips",
        "mat_concealed_toilet_cistern_flushing_system" to "mat_concealed_cistern_frame",

        "mat_cement_block_solid" to "mat_solid_cement_brick",
        "mat_galvanized_ties" to "mat_brick_ties_galv",
        "mat_fiber_mesh" to "mat_fiber_mesh_160",
        "mat_addibond_65" to "mat_latex_bonding_additive",
        "mat_sand_washed" to "mat_clean_sand",
        "mat_polyurethane_sealant" to "mat_polyurethane_joint_and_crack_sealant",
        "mat_ppr_brass_fittings" to "mat_ppr_fittings",
        "mat_armacell_insulation" to "mat_pipe_support_insulation",
        "mat_magic_boxes" to "mat_magic_boxes_conduits",
        "mat_flexible_conduits" to "mat_magic_boxes_conduits",
        "mat_epoxy_grout" to "mat_epoxy_tile_grout",
        "mat_water_sealer" to "mat_wall_primer",
        "mat_jotun_fenomastic" to "mat_finish_wall_paint",
        "mat_omega_steel_profiles" to "mat_gypsum_metal_framing",
        "mat_gypsum_joint_filler" to "mat_drywall_joint_compound_tape",
        "mat_muski_wood" to "mat_wooden_subframe_doors",
        "mat_aluminum_jumbo" to "mat_aluminum_jumbo_profiles",
        "mat_schneider_mcb" to "mat_mcb"
    )

    private fun append(original: String, supplement: String): String =
        if (original.contains(supplement)) original else original + "\n\n" + supplement

    // These phases prepare the substrate before the existing installation stage.
    private val order = mapOf(
        6 to emptyList<String>(),
        15 to listOf("stage_review_70", "stage_tiles_02", "stage_flooring_01", "stage_review_71", "stage_review_72", "stage_tiles_03"),
        16 to listOf("stage_review_73", "stage_review_75", "stage_review_74", "stage_marble_01", "stage_marble_02", "stage_marble_03"),
        17 to listOf("stage_woodfloor_02", "stage_woodfloor_01", "stage_review_76", "stage_review_77", "stage_woodfloor_03"),
        18 to listOf("stage_review_78", "stage_paint_01", "stage_review_79", "stage_review_80", "stage_paint_02", "stage_review_81", "stage_paint_03", "stage_review_82", "stage_review_83"),
        19 to listOf("stage_review_85", "stage_gypsum_01", "stage_gypsum_02", "stage_gypsum_03", "stage_review_84"),
        20 to listOf("stage_review_86", "stage_decor_03", "stage_decor_02", "stage_decor_01"),
        21 to listOf("stage_review_87", "stage_carpentry_01", "stage_review_88", "stage_review_89", "stage_carpentry_02", "stage_carpentry_03"),
        22 to listOf("stage_review_90", "stage_review_91", "stage_review_92", "stage_aluminum_01", "stage_aluminum_02", "stage_aluminum_03"),
        24 to listOf("stage_kitchen_01", "stage_review_99", "stage_kitchen_02", "stage_review_100", "stage_kitchen_03", "stage_review_101"),
        25 to listOf("stage_review_102", "stage_shutter_02", "stage_shutter_03", "stage_review_103", "stage_shutter_01"),
        26 to listOf("stage_metal_02", "stage_metal_01", "stage_metal_03"),
        27 to listOf("stage_adhesives_03", "stage_adhesives_01", "stage_adhesives_02"),
        29 to listOf("stage_qa_tests_02", "stage_qa_tests_01", "stage_qa_tests_03")
    )

    private val stagesWithNotes: List<ExecutionStage> = originalStages + addedStages

    val stages: List<ExecutionStage> = stagesWithNotes.groupBy { it.categoryId }
        .toSortedMap().values.flatMap { categoryStages ->
            val categoryId = categoryStages.first().categoryId
            val categoryOrder = ((order[categoryId] ?: emptyList()) + (TechnicalStageOrder.preparation[categoryId] ?: emptyList())).distinct()
            categoryStages.sortedBy { stage ->
                categoryOrder.indexOf(stage.id).takeIf { it >= 0 } ?: (1000 + stage.stageNumber)
            }.mapIndexed { index, stage ->
                val linked = rawMaterials.filter { material ->
                    material.relatedStageIds.any { (aliases[it] ?: it) == stage.id }
                }.map { it.id }
                stage.copy(
                    stageNumber = index + 1,
                    relatedMaterialIds = (stage.relatedMaterialIds.map { materialAliases[it] ?: it } + linked).distinct()
                )
            }
        }

    val materials: List<MaterialCard> = rawMaterials.map { material ->
        material.copy(relatedStageIds = stages.filter { material.id in it.relatedMaterialIds }.map { it.id })
    }

    // Read-only inspection guidance for phases absent from the original checklists.
    val checklistItems: List<QualityCheckItem> = FinishingChecklistsData.checkItems.map { check ->
        val stage = stages.first { it.id == check.stageId }
        check.copy(
            technicalReference = check.technicalReference + "\n" + stage.codeReferences.joinToString("\n")
        )
    } +
        stages.flatMap { stage ->
                val originalPhases = FinishingChecklistsData.checkItems.filter { it.stageId == stage.id }.map { it.phase }.toSet()
                if (stage.categoryId in setOf(22, 25)) Batch10Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId in setOf(21, 24)) Batch09Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId in 18..20) Batch08Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId in 15..17) Batch07Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId in setOf(12, 14)) Batch06Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId in 9..11) Batch05Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId in setOf(8, 13)) Batch04Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (stage.categoryId == 7) Batch03Checkpoints.forStage(stage).filter { it.phase !in originalPhases }
                else if (originalPhases.isNotEmpty()) emptyList()
                else if (stage.categoryId in 1..3) Batch01Checkpoints.forStage(stage)
                else if (stage.categoryId in 4..6) Batch02Checkpoints.forStage(stage)
                else listOf(QualityCheckItem(
                    id = "check_guide_${stage.id}", categoryId = stage.categoryId, stageId = stage.id,
                    titleAr = stage.titleAr, phase = InspectionPhase.AFTER,
                    method = stage.qualityTests.joinToString("\n"),
                    toolUsed = stage.toolsNeeded.joinToString(" • "),
                    acceptanceCriterion = stage.handoverCriteria.joinToString("\n"),
                    technicalReference = stage.codeReferences.joinToString("\n")
                ))
            }

    val categories: List<FinishingCategory> = FinishingCategoriesData.categories.map { category ->
        category.copy(
            stagesCount = stages.count { it.categoryId == category.id },
            materialsCount = materials.count { category.id in it.categoryIds },
            defectsCount = FinishingDefectsData.defects.count { it.categoryId == category.id },
            checklistsCount = checklistItems.count { it.categoryId == category.id }
        )
    }
}

