package com.example

import android.app.Application
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.theme.FayrozTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class NavigationStateTest {
    @get:Rule val compose = createComposeRule()
    private fun start(screen: Screen): FinishingViewModel {
        val vm = FinishingViewModel(ApplicationProvider.getApplicationContext<Application>())
        vm.navigateTo(screen)
        compose.setContent { FayrozTheme { FayrozAppRoot(vm) } }
        compose.waitForIdle()
        return vm
    }

    @Test fun categoryListReturnsToSameScrollPosition() {
        val vm = start(Screen.CategoriesList)
        compose.onNodeWithTag("categories_list").performScrollToIndex(20)
        compose.waitForIdle()
        val before = compose.onNodeWithTag("categories_list").fetchSemanticsNode()
            .config[SemanticsProperties.VerticalScrollAxisRange].value()
        assertTrue(before > 0f)
        compose.runOnIdle { vm.navigateTo(Screen.CategoryDetail(21)) }
        compose.waitForIdle()
        compose.runOnIdle { assertTrue(vm.navigateBack()) }
        compose.waitForIdle()
        val after = compose.onNodeWithTag("categories_list").fetchSemanticsNode()
            .config[SemanticsProperties.VerticalScrollAxisRange].value()
        assertEquals(before, after, 1f)
    }

    @Test fun categoryFilterSurvivesOpeningDetail() {
        val vm = start(Screen.CategoriesList)
        val title = vm.repository.getCategoryById(7)!!.titleAr
        compose.onNodeWithTag("categories_search_input").performTextInput(title)
        compose.onNodeWithTag("cat_card_7").performClick()
        compose.waitForIdle()
        compose.runOnIdle { vm.navigateBack() }
        compose.waitForIdle()
        compose.onNodeWithTag("categories_search_input").assertTextContains(title)
        compose.onNodeWithTag("cat_card_7").assertIsDisplayed()
        compose.onNodeWithTag("cat_card_1").assertDoesNotExist()
    }

    @Test fun materialTabSurvivesOpeningMaterialAndBack() {
        val vm = start(Screen.CategoryDetail(7))
        compose.onNodeWithTag("cat_tab_1").performClick()
        compose.onNodeWithTag("cat_tab_1").assertIsSelected()
        val material = vm.repository.getMaterialsByCategoryId(7).first()
        compose.runOnIdle { vm.navigateTo(Screen.MaterialDetail(material.id)) }
        compose.waitForIdle()
        compose.runOnIdle { vm.navigateBack() }
        compose.waitForIdle()
        compose.onNodeWithTag("cat_tab_1").assertIsSelected()
    }
}
