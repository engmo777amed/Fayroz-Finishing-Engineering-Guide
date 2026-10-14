package com.example.data

import com.example.model.ExecutionStage
import com.example.model.QualityCheckItem

object Batch03Checkpoints {
    fun forStage(stage: ExecutionStage): List<QualityCheckItem> =
        Batch01Checkpoints.forStage(stage).map { checkpoint ->
            checkpoint.copy(
                id = checkpoint.id.replace("check_batch01_", "check_batch03_"),
                technicalReference = (checkpoint.technicalReference.lines() + Batch03References.forCategory(stage.categoryId))
                    .filter { it.isNotBlank() }.distinct().joinToString("\n")
            )
        }
}
