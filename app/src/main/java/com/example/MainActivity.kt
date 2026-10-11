package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.FayrozBottomNav
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.CategoryDetailScreen
import com.example.ui.screens.DefectDetailScreen
import com.example.ui.screens.DimensionsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MaterialDetailScreen
import com.example.ui.screens.MaterialsLibraryScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.screens.StageDetailScreen
import com.example.ui.screens.TroubleshootingListScreen
import com.example.ui.theme.FayrozTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FayrozTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    FayrozAppRoot()
                }
            }
        }
    }
}

@Composable
fun FayrozAppRoot(
    viewModel: FinishingViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsState()

    // Handle back button for sub-screens
    BackHandler(enabled = currentScreen !is Screen.Home) {
        viewModel.navigateBack()
    }

    val showBottomNav = currentScreen is Screen.Home ||
            currentScreen is Screen.CategoriesList ||
            currentScreen is Screen.MaterialsLibrary ||
            currentScreen is Screen.TroubleshootingList

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNav) {
                FayrozBottomNav(
                    currentScreen = currentScreen,
                    onNavigate = { screen ->
                        viewModel.navigateTo(screen)
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) }
                    )
                }
                is Screen.CategoriesList -> {
                    CategoriesScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.CategoryDetail -> {
                    CategoryDetailScreen(
                        categoryId = screen.categoryId,
                        initialTab = screen.initialTab,
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.StageDetail -> {
                    StageDetailScreen(
                        stageId = screen.stageId,
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.MaterialDetail -> {
                    MaterialDetailScreen(
                        materialId = screen.materialId,
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.DefectDetail -> {
                    DefectDetailScreen(
                        defectId = screen.defectId,
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.MaterialsLibrary -> {
                    MaterialsLibraryScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.TroubleshootingList -> {
                    TroubleshootingListScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.DimensionsList -> {
                    DimensionsScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.Search -> {
                    SearchScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
                is Screen.Bookmarks -> {
                    BookmarksScreen(
                        viewModel = viewModel,
                        onNavigate = { viewModel.navigateTo(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }
            }
        }
    }
}
