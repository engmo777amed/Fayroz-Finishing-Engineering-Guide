package com.example.data

import com.example.model.ExecutionStage
import com.example.model.QualityCheckItem

/** Same earlier hold-point structure, with separate stable IDs for newly reviewed batch02 points. */
object Batch02Checkpoints {
    fun forStage(stage: ExecutionStage): List<QualityCheckItem> =
        Batch01Checkpoints.forStage(stage).map { check ->
            check.copy(id = check.id.replace("check_batch01_", "check_batch02_"))
        }
}
