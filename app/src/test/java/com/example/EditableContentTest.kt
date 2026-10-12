package com.example

import android.content.Context
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.data.*
import com.example.ui.components.*
import com.example.ui.theme.FayrozTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class EditableContentTest {
    @get:Rule val compose = createComposeRule()
    private val context get() = ApplicationProvider.getApplicationContext<Context>()
    @Before fun clearEdits() { context.getSharedPreferences("fayroz_text_edits_v1", Context.MODE_PRIVATE).edit().clear().commit() }

    @Test fun editsSurviveStoreRecreationAndNewBundledText() {
        val first = EditableContentStore(context)
        assertTrue(first.save("stage|stable|objective", "شرحي المعدل"))
        val recreated = EditableContentStore(context)
        assertEquals("شرحي المعدل", recreated.text("stage", "stable", "objective", "أصل جديد بعد تحديث التطبيق"))
        assertTrue(recreated.reset("stage|stable|objective"))
        assertEquals("أصل جديد", recreated.text("stage", "stable", "objective", "أصل جديد"))
        assertFalse(recreated.save("stage|stable|objective", "   "))
    }

    @Test fun repositoryReadsEditsAndSearchUsesEditedTitles() {
        val repository = FinishingRepository(context)
        val stage = repository.allStages.first()
        val key = "stage|${stage.id}|titleAr"
        assertTrue(repository.textStore.save(key, "عنوان مخصص فريد للتشطيب"))
        assertEquals("عنوان مخصص فريد للتشطيب", repository.getStageById(stage.id)!!.titleAr)
        assertTrue(repository.search("عنوان مخصص فريد").stages.any { it.id == stage.id })
        assertEquals(key, repository.textStore.target("عنوان مخصص فريد للتشطيب", "category|${stage.categoryId}", "row").key)
    }

    @Test fun longPressEditorSavesVisibleTextAndRestoresOriginal() {
        val store = EditableContentStore(context)
        compose.setContent { FayrozTheme { CompositionLocalProvider(LocalContentStore provides store) { EditableText("شرح الصورة", textKey = "test.figure.caption") } } }
        compose.onNodeWithText("شرح الصورة").performTouchInput { longClick() }
        compose.onNode(hasSetTextAction()).performTextClearance()
        compose.onNode(hasSetTextAction()).performTextInput("شرح معدل للصورة")
        compose.onNodeWithText("حفظ").performClick()
        compose.onNodeWithText("شرح معدل للصورة").assertIsDisplayed()
        compose.onNodeWithText("شرح معدل للصورة").performTouchInput { longClick() }
        compose.onNodeWithText("استرجاع الأصل").performClick()
        compose.onNodeWithText("شرح الصورة").assertIsDisplayed()
    }

    @Test fun everyCategoryHasPracticalContentAndDefectDiagnosis() {
        for (category in FinishingCatalog.categories) {
            assertTrue(category.titleAr, PracticalGuideData.entries.count { it.categoryId == category.id } >= 4)
            assertTrue(category.titleAr, FinishingDefectsData.defects.any { it.categoryId == category.id })
        }
        assertEquals(125, PracticalGuideData.entries.size)
        val store = EditableContentStore(context)
        val keys = store.records.flatMap { it.fields }.map { it.key }
        assertEquals(keys.size, keys.distinct().size)
        assertTrue(keys.contains("figure|wet_floor|dimensionText"))
        assertFalse(keys.any { it.endsWith("|assetName") || it.endsWith("|id") })
    }

    @Test fun illustrationsHaveBundledReadableAssetsAndQualifiedDimensions() {
        assertEquals(14, TechnicalIllustrations.figures.size)
        for (figure in TechnicalIllustrations.figures) {
            context.assets.open("figures/${figure.assetName}").use { assertTrue(it.available() > 1000) }
            assertTrue(figure.limits.contains("غير مرسومة بمقياس"))
            assertTrue(figure.dimensionText.isNotBlank())
            assertTrue(figure.categoryIds.all { it in 1..30 })
        }
        assertTrue(TechnicalIllustrations.figures.single { it.id == "wet_floor" }.dimensionText.contains("3–6"))
        assertTrue(TechnicalIllustrations.figures.single { it.id == "wood_floor" }.dimensionText.contains("Quick-Step"))
    }
}
