package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InspectionDao {
    @Query("SELECT * FROM inspection_reports ORDER BY createdAt DESC")
    fun getAllReports(): Flow<List<InspectionReportEntity>>

    @Query("SELECT * FROM inspection_reports WHERE id = :id LIMIT 1")
    suspend fun getReportById(id: Long): InspectionReportEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: InspectionReportEntity): Long

    @Delete
    suspend fun deleteReport(report: InspectionReportEntity)

    @Query("DELETE FROM inspection_reports WHERE id = :id")
    suspend fun deleteReportById(id: Long)
}

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    fun isBookmarked(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmarkById(id: String)
}
