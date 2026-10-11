package com.example.data

import android.content.Context
import com.example.data.local.AppDatabase
import com.example.data.local.BookmarkEntity
import com.example.model.ExecutionStage
import com.example.model.FinishingCategory
import com.example.model.MaterialCard
import com.example.model.QualityCheckItem
import com.example.model.TechnicalDimension
import com.example.model.TroubleshootingDefect
import kotlinx.coroutines.flow.Flow

data class SearchResult(
    val stages: List<ExecutionStage>,
    val materials: List<MaterialCard>,
    val defects: List<TroubleshootingDefect>,
    val dimensions: List<TechnicalDimension>,
    val categories: List<FinishingCategory>
) {
    val totalCount: Int
        get() = stages.size + materials.size + defects.size + dimensions.size + categories.size
}

class FinishingRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context)
    private val bookmarkDao = db.bookmarkDao()

    val allCategories: List<FinishingCategory> = FinishingCatalog.categories

    val allStages: List<ExecutionStage> = FinishingCatalog.stages

    val allMaterials: List<MaterialCard> = FinishingCatalog.materials

    val allDefects: List<TroubleshootingDefect> = FinishingDefectsData.defects

    val allChecklistItems: List<QualityCheckItem> = FinishingChecklistsData.checkItems

    val allDimensions: List<TechnicalDimension> = FinishingDimensionsData.dimensions

    // Categories
    fun getCategoryById(id: Int): FinishingCategory? =
        allCategories.find { it.id == id }

    // Stages
    fun getStagesByCategoryId(categoryId: Int): List<ExecutionStage> =
        allStages.filter { it.categoryId == categoryId }.sortedBy { it.stageNumber }

    fun getStageById(stageId: String): ExecutionStage? =
        allStages.find { it.id == stageId }

    // Materials
    fun getMaterialsByCategoryId(categoryId: Int): List<MaterialCard> =
        allMaterials.filter { it.categoryIds.contains(categoryId) }

    fun getMaterialById(id: String): MaterialCard? =
        allMaterials.find { it.id == id }

    fun getMaterialsByIds(ids: List<String>): List<MaterialCard> =
        allMaterials.filter { ids.contains(it.id) }

    // Checklists
    fun getChecklistItemsByCategoryId(categoryId: Int): List<QualityCheckItem> =
        allChecklistItems.filter { it.categoryId == categoryId }

    fun getChecklistItemsByStageId(stageId: String): List<QualityCheckItem> =
        allChecklistItems.filter { it.stageId == stageId }

    // Defects
    fun getDefectsByCategoryId(categoryId: Int): List<TroubleshootingDefect> =
        allDefects.filter { it.categoryId == categoryId }

    fun getDefectById(id: String): TroubleshootingDefect? =
        allDefects.find { it.id == id }

    // Dimensions
    fun getDimensionsByCategoryId(categoryId: Int): List<TechnicalDimension> =
        allDimensions.filter { it.categoryId == categoryId }

    // Smart Engineering Search with Arabic normalization & Synonyms
    fun search(rawQuery: String): SearchResult {
        val q = normalizeArabic(rawQuery.trim().lowercase())
        if (q.isBlank()) {
            return SearchResult(emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
        }

        val matchedCategories = allCategories.filter {
            normalizeArabic(it.titleAr).contains(q) ||
            it.titleEn.lowercase().contains(q) ||
            normalizeArabic(it.summaryAr).contains(q)
        }

        val matchedStages = allStages.filter {
            normalizeArabic(it.titleAr).contains(q) ||
            normalizeArabic(it.objective).contains(q) ||
            it.executionSteps.any { step -> normalizeArabic(step).contains(q) } ||
            it.technicalRequirements.any { requirement -> normalizeArabic(requirement).contains(q) } ||
            it.qualityTests.any { test -> normalizeArabic(test).contains(q) } ||
            normalizeArabic(it.dimensionsAndSlopes).contains(q) ||
            normalizeArabic(it.mixesAndRatios).contains(q) ||
            it.siteTips.any { tip -> normalizeArabic(tip).contains(q) } ||
            it.commonMistakes.any { mistake -> normalizeArabic(mistake).contains(q) } ||
            it.requiredMaterials.any { mat -> normalizeArabic(mat).contains(q) }
        }

        val matchedMaterials = allMaterials.filter {
            normalizeArabic(it.marketNameAr).contains(q) ||
            normalizeArabic(it.technicalNameAr).contains(q) ||
            it.tradeNames.any { tn -> normalizeArabic(tn).contains(q) } ||
            it.egyptianBrands.any { brand -> normalizeArabic(brand).contains(q) } ||
            normalizeArabic(it.definitionAndFunction).contains(q) ||
            normalizeArabic(it.dimensionsAndSpecs).contains(q) ||
            normalizeArabic(it.counterfeitDetection).contains(q)
        }

        val matchedDefects = allDefects.filter {
            normalizeArabic(it.titleAr).contains(q) ||
            normalizeArabic(it.marketName).contains(q) ||
            normalizeArabic(it.description).contains(q) ||
            normalizeArabic(it.rootCauseDiagnosis).contains(q) ||
            it.probableCauses.any { c -> normalizeArabic(c).contains(q) } ||
            it.repairMaterials.any { rm -> normalizeArabic(rm).contains(q) }
        }

        val matchedDimensions = allDimensions.filter {
            normalizeArabic(it.itemTitle).contains(q) ||
            normalizeArabic(it.standardValue).contains(q) ||
            normalizeArabic(it.practicalNotes).contains(q) ||
            normalizeArabic(it.referenceSource).contains(q)
        }

        return SearchResult(
            stages = matchedStages,
            materials = matchedMaterials,
            defects = matchedDefects,
            dimensions = matchedDimensions,
            categories = matchedCategories
        )
    }

    private fun normalizeArabic(text: String): String {
        return text.lowercase()
            .replace('أ', 'ا')
            .replace('إ', 'ا')
            .replace('آ', 'ا')
            .replace('ة', 'ه')
            .replace('ى', 'ي')
            .replace("ـ", "") // Tatweel
            .replace(Regex("[\u064B-\u065F]"), "")
    }

    // Bookmarks persistence
    fun getAllBookmarks(): Flow<List<BookmarkEntity>> =
        bookmarkDao.getAllBookmarks()

    fun isBookmarked(id: String): Flow<Boolean> =
        bookmarkDao.isBookmarked(id)

    suspend fun toggleBookmark(bookmark: BookmarkEntity, currentlyBookmarked: Boolean) {
        if (currentlyBookmarked) {
            bookmarkDao.deleteBookmarkById(bookmark.id)
        } else {
            bookmarkDao.insertBookmark(bookmark)
        }
    }
}
