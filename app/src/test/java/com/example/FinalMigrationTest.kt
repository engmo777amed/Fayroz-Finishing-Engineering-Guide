package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.*
import org.json.JSONArray
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class FinalMigrationTest {
    private fun manifest(context: Context) = JSONArray(
        context.assets.open("content_review_edit_migration.json").bufferedReader().use { it.readText() }
    )

    @Test fun manifestRevisedListsMatchActualFinalCatalog() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        context.getSharedPreferences("fayroz_text_edits_v1", Context.MODE_PRIVATE).edit().clear().commit()
        val store = EditableContentStore(context)
        val fields = store.records.flatMap { it.fields }.associateBy { it.key }
        val groups = manifest(context)
        assertEquals(1908, groups.length())
        val scopes = mutableSetOf<String>()
        for (g in 0 until groups.length()) {
            val group = groups.getJSONObject(g)
            val scope = group.getString("scope")
            assertTrue(scope, scopes.add(scope))
            assertFalse(scope, scope.endsWith("|relatedMaterialIds") || scope.endsWith("|relatedStageIds") || scope.endsWith("|categoryIds"))
            val revised = group.getJSONArray("new")
            val actual = fields.values.filter { it.key.startsWith("$scope|") }
                .sortedBy { it.key.substringAfterLast("|").toInt() }.map { it.original }
            assertEquals(scope, List(revised.length()) { revised.getString(it) }, actual)
        }
    }

    @Test fun everyOldPointEditIsRetainedOnceAndMigrationIsIdempotent() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val prefs = context.getSharedPreferences("fayroz_text_edits_v1", Context.MODE_PRIVATE)
        prefs.edit().clear().commit()
        val groups = manifest(context)
        val editor = prefs.edit()
        val expected = linkedMapOf<String, String>()
        for (g in 0 until groups.length()) {
            val group = groups.getJSONObject(g)
            val scope = group.getString("scope")
            val oldArray = group.getJSONArray("old")
            val newArray = group.getJSONArray("new")
            val old = List(oldArray.length()) { oldArray.getString(it) }
            val revised = List(newArray.length()) { newArray.getString(it) }
            for (index in old.indices) {
                val key = "$scope|$index"
                val edit = "user edit $g/$index"
                editor.putString(key, edit)
                val target = ContentReviewEditMigration.targetIndex(old, revised, index)
                val destination = if (target == null) "archived|$key" else "$scope|$target"
                assertNull(destination, expected.put(destination, edit))
            }
        }
        editor.putString("stage|stage_carpentry_01|objective", "scalar user edit").commit()
        EditableContentStore(context)
        expected.forEach { (key, value) -> assertEquals(key, value, prefs.getString(key, null)) }
        assertEquals("scalar user edit", prefs.getString("stage|stage_carpentry_01|objective", null))
        assertEquals(expected.size, prefs.all.values.count { it is String && it.startsWith("user edit ") })
        val snapshot = prefs.all.toMap()
        EditableContentStore(context)
        assertEquals(snapshot, prefs.all)
    }
}
