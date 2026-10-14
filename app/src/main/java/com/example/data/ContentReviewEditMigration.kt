package com.example.data

/** Never attach an old positional edit to a newly worded or reordered point. */
object ContentReviewEditMigration {
    fun targetIndex(old: List<String>, revised: List<String>, index: Int): Int? {
        val text = old.getOrNull(index) ?: return null
        if (old.count { it == text } != 1 || revised.count { it == text } != 1) return null
        return revised.indexOf(text)
    }
}
