package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FinishingRepository
import com.example.data.SearchResult
import com.example.data.local.BookmarkEntity
import com.example.model.ExecutionStage
import com.example.model.FinishingCategory
import com.example.model.MaterialCard
import com.example.model.QualityCheckItem
import com.example.model.TechnicalDimension
import com.example.model.TroubleshootingDefect
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
    data object Search : Screen()
    data object Bookmarks : Screen()
    data object ContentEditor : Screen()
    data class PracticalGuide(val categoryId: Int? = null) : Screen()
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

    init {
        viewModelScope.launch { repository.textStore.revision.collect { updateSearchQuery(_searchQuery.value) } }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _searchResults.value = repository.search(query)
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

}
