package com.example.data

import com.example.model.ExecutionStage
import com.example.model.QualityCheckItem

object Batch05Checkpoints {
    fun forStage(stage: ExecutionStage): List<QualityCheckItem> =
        Batch01Checkpoints.forStage(stage).map { checkpoint ->
            checkpoint.copy(
                id = checkpoint.id.replace("check_batch01_", "check_batch05_"),
                technicalReference = (checkpoint.technicalReference.lines() +
                    Batch05References.forCategory(stage.categoryId)).distinct().joinToString("\n")
            )
        }
}
