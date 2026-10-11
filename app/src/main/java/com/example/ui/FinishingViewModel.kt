package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FinishingRepository
import com.example.data.SearchResult
import com.example.data.local.BookmarkEntity
import com.example.data.local.InspectionReportEntity
import com.example.model.ExecutionStage
import com.example.model.FinishingCategory
import com.example.model.InspectionCheckResult
import com.example.model.InspectionStatus
import com.example.model.MaterialCard
import com.example.model.QualityCheckItem
import com.example.model.TechnicalDimension
import com.example.model.TroubleshootingDefect
import com.example.util.FinishingCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    data object Home : Screen()
    data object CategoriesList : Screen()
    data class CategoryDetail(val categoryId: Int, val initialTab: Int = 0) : Screen()
    data class StageDetail(val stageId: String) : Screen()
    data class MaterialDetail(val materialId: String) : Screen()
    data class DefectDetail(val defectId: String) : Screen()
    data object MaterialsLibrary : Screen()
    data object TroubleshootingList : Screen()
    data object DimensionsList : Screen()
    data object InspectionHub : Screen()
    data class ActiveInspection(val stageId: String, val categoryId: Int) : Screen()
    data class ViewReport(val reportId: Long) : Screen()
    data object Search : Screen()
    data object Calculator : Screen()
    data object Bookmarks : Screen()
}

class FinishingViewModel(application: Application) : AndroidViewModel(application) {
    val repository = FinishingRepository(application)

    // Navigation State Stack
    private val _screenStack = MutableStateFlow<List<Screen>>(listOf(Screen.Home))
    val screenStack: StateFlow<List<Screen>> = _screenStack.asStateFlow()

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value == screen) return
        if (screen is Screen.Home) {
            _screenStack.value = listOf(Screen.Home)
            _currentScreen.value = Screen.Home
            return
        }
        _screenStack.value = _screenStack.value + screen
        _currentScreen.value = screen
    }

    fun navigateBack(): Boolean {
        if (_screenStack.value.size > 1) {
            val newStack = _screenStack.value.dropLast(1)
            _screenStack.value = newStack
            _currentScreen.value = newStack.last()
            return true
        }
        return false
    }

    fun canNavigateBack(): Boolean = _screenStack.value.size > 1

    // Search
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow(SearchResult(emptyList(), emptyList(), emptyList(), emptyList(), emptyList()))
    val searchResults = _searchResults.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _searchResults.value = repository.search(query)
    }

    // Active Inspection Checklist State
    private val _activeInspectionItems = MutableStateFlow<List<InspectionCheckResult>>(emptyList())
    val activeInspectionItems = _activeInspectionItems.asStateFlow()

    private val _activeProjectName = MutableStateFlow("مشروع فيلا سكنية - التجمع الخامس")
    val activeProjectName = _activeProjectName.asStateFlow()

    private val _activeZoneName = MutableStateFlow("الدور الأرضي - حمام الضيوف")
    val activeZoneName = _activeZoneName.asStateFlow()

    private val _activeEngineerName = MutableStateFlow("م. مهندس الموقع")
    val activeEngineerName = _activeEngineerName.asStateFlow()

    private val _activeGeneralNotes = MutableStateFlow("")
    val activeGeneralNotes = _activeGeneralNotes.asStateFlow()

    fun setProjectName(name: String) { _activeProjectName.value = name }
    fun setZoneName(name: String) { _activeZoneName.value = name }
    fun setEngineerName(name: String) { _activeEngineerName.value = name }
    fun setGeneralNotes(notes: String) { _activeGeneralNotes.value = notes }

    fun startInspection(stageId: String, categoryId: Int) {
        val rawItems = repository.getChecklistItemsByStageId(stageId)
        val fallbackItems = if (rawItems.isEmpty()) {
            repository.getChecklistItemsByCategoryId(categoryId)
        } else rawItems

        _activeInspectionItems.value = fallbackItems.map {
            InspectionCheckResult(
                checkItemId = it.id,
                checkItemTitle = it.titleAr,
                phaseTitle = it.phase.titleAr,
                toolUsed = it.toolUsed,
                criterion = it.acceptanceCriterion,
                status = InspectionStatus.PENDING,
                notes = ""
            )
        }
        navigateTo(Screen.ActiveInspection(stageId, categoryId))
    }

    fun updateCheckItemStatus(checkItemId: String, newStatus: InspectionStatus) {
        _activeInspectionItems.value = _activeInspectionItems.value.map {
            if (it.checkItemId == checkItemId) it.copy(status = newStatus) else it
        }
    }

    fun updateCheckItemNotes(checkItemId: String, notes: String) {
        _activeInspectionItems.value = _activeInspectionItems.value.map {
            if (it.checkItemId == checkItemId) it.copy(notes = notes) else it
        }
    }

    fun saveCurrentReport(stageId: String, categoryId: Int, onSaved: (Long) -> Unit) {
        viewModelScope.launch {
            val stage = repository.getStageById(stageId)
            val category = repository.getCategoryById(categoryId)
            val items = _activeInspectionItems.value

            val total = items.size
            val passed = items.count { it.status == InspectionStatus.PASSED }
            val failed = items.count { it.status == InspectionStatus.FAILED }
            val pending = items.count { it.status == InspectionStatus.PENDING }
            val na = items.count { it.status == InspectionStatus.NOT_APPLICABLE }

            val itemsSerialized = items.joinToString(";;") {
                "${it.checkItemTitle}::${it.status.name}::${it.criterion}::${it.toolUsed}::${it.notes}"
            }

            val entity = InspectionReportEntity(
                projectName = _activeProjectName.value,
                unitOrZone = _activeZoneName.value,
                categoryId = categoryId,
                categoryTitle = category?.titleAr ?: "قسم التشطيبات",
                stageId = stageId,
                stageTitle = stage?.titleAr ?: "مرحلة التنفيذ",
                engineerName = _activeEngineerName.value,
                inspectionDate = "2026/10/10",
                totalItems = total,
                passedItems = passed,
                failedItems = failed,
                pendingItems = pending,
                naItems = na,
                generalNotes = _activeGeneralNotes.value,
                itemsJson = itemsSerialized
            )

            val id = repository.saveInspectionReport(entity)
            onSaved(id)
        }
    }

    // Reports Flow
    val allReports: StateFlow<List<InspectionReportEntity>> =
        repository.getAllInspectionReports().stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun deleteReport(id: Long) {
        viewModelScope.launch {
            repository.deleteInspectionReport(id)
        }
    }

    // Bookmarks Flow
    val allBookmarks: StateFlow<List<BookmarkEntity>> =
        repository.getAllBookmarks().stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    val bookmarkedIds: StateFlow<Set<String>> =
        repository.getAllBookmarks()
            .map { list -> list.map { it.id }.toSet() }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                emptySet()
            )

    fun isBookmarked(id: String): StateFlow<Boolean> {
        return bookmarkedIds
            .map { it.contains(id) }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                bookmarkedIds.value.contains(id)
            )
    }

    fun isItemBookmarked(id: String): Boolean = bookmarkedIds.value.contains(id)

    fun toggleBookmark(id: String, type: String, title: String, subtitle: String, categoryTitle: String, isCurrently: Boolean) {
        viewModelScope.launch {
            val entity = BookmarkEntity(
                id = id,
                itemType = type,
                itemId = id,
                title = title,
                subtitle = subtitle,
                categoryTitle = categoryTitle
            )
            repository.toggleBookmark(entity, isCurrently)
        }
    }

    // Calculator State
    val calcType = MutableStateFlow(0) // 0: Plaster, 1: Tiles, 2: Paint, 3: Gypsum, 4: Masonry
    val calcAreaInput = MutableStateFlow("100")
    val calcThicknessInput = MutableStateFlow("2.0")
    val calcIsPorcelain = MutableStateFlow(true)
    val calcIsRedBrick = MutableStateFlow(true)

    fun calculatePlasterResult() = FinishingCalculator.calculatePlaster(
        calcAreaInput.value.toDoubleOrNull() ?: 100.0,
        calcThicknessInput.value.toDoubleOrNull() ?: 2.0
    )

    fun calculateTilesResult() = FinishingCalculator.calculateTiles(
        calcAreaInput.value.toDoubleOrNull() ?: 100.0,
        7.0,
        calcIsPorcelain.value
    )

    fun calculatePaintResult() = FinishingCalculator.calculatePaint(
        calcAreaInput.value.toDoubleOrNull() ?: 100.0,
        3
    )

    fun calculateGypsumResult() = FinishingCalculator.calculateGypsum(
        calcAreaInput.value.toDoubleOrNull() ?: 100.0
    )

    fun calculateMasonryResult() = FinishingCalculator.calculateMasonry(
        calcAreaInput.value.toDoubleOrNull() ?: 100.0,
        calcIsRedBrick.value
    )
}
