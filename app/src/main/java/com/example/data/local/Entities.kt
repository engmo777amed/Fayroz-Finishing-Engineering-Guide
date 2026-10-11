package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inspection_reports")
data class InspectionReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val projectName: String,
    val unitOrZone: String,
    val categoryId: Int,
    val categoryTitle: String,
    val stageId: String,
    val stageTitle: String,
    val engineerName: String,
    val inspectionDate: String,
    val totalItems: Int,
    val passedItems: Int,
    val failedItems: Int,
    val pendingItems: Int,
    val naItems: Int,
    val generalNotes: String,
    val itemsJson: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val id: String, // e.g. "stage:stage_plaster_02"
    val itemType: String, // "STAGE", "MATERIAL", "DEFECT", "DIMENSION"
    val itemId: String,
    val title: String,
    val subtitle: String,
    val categoryTitle: String,
    val createdAt: Long = System.currentTimeMillis()
)
