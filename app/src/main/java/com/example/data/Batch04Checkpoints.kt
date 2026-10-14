package com.example.data

import com.example.model.ExecutionStage
import com.example.model.QualityCheckItem

object Batch04Checkpoints {
    fun forStage(stage: ExecutionStage): List<QualityCheckItem> =
        Batch01Checkpoints.forStage(stage).map { checkpoint ->
            checkpoint.copy(
                id = checkpoint.id.replace("check_batch01_", "check_batch04_"),
                technicalReference = (checkpoint.technicalReference.lines() +
                    Batch04References.forCategory(stage.categoryId)).distinct().joinToString("\n")
            )
        }
}
