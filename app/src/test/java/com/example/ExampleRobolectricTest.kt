package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.FinishingRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("FAYROZ Finishing", appName)
  }

  @Test
  fun `all 30 categories exist and are accessible`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val repo = FinishingRepository(context)
    val categories = repo.allCategories
    assertEquals("Should have exactly 30 finishing categories", 30, categories.size)

    for (cat in categories) {
      val found = repo.getCategoryById(cat.id)
      assertNotNull("Category ${cat.id} must be retrievable by id", found)
      assertTrue("Category title must not be empty", cat.titleAr.isNotBlank())
      assertTrue("Category code must start with SEC-", cat.code.startsWith("SEC-"))
      val stages = repo.getStagesByCategoryId(cat.id)
      assertTrue("Category ${cat.id} (${cat.titleAr}) should have execution stages", stages.isNotEmpty())
    }
  }

  @Test
  fun `repository search finds egyptian market terms`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val repo = FinishingRepository(context)

    // Test searching "بؤجة"
    val resPlaster = repo.search("بؤجة")
    assertTrue("Should find plaster stages or checklists", resPlaster.stages.isNotEmpty() || resPlaster.categories.isNotEmpty())

    // Test searching "PPR"
    val resPpr = repo.search("PPR")
    assertTrue("Should find PPR pipes material", resPpr.materials.isNotEmpty())

    // Test searching "تطبيل"
    val resDefect = repo.search("تطبيل")
    assertTrue("Should find hollow tile defect", resDefect.defects.isNotEmpty())
  }
}
