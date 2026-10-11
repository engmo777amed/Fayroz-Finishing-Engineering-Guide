package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.FayrozTopBar
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozTurquoise

@Composable
fun BookmarksScreen(
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val repository = viewModel.repository
    val bookmarks by viewModel.allBookmarks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "المفضلة والمراجع السريعة",
            subtitle = "${bookmarks.size} بند محفوظ",
            showBackButton = true,
            onBackClick = onBack
        )

        if (bookmarks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = FayrozGold,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "لا توجد موضوعات محفوظة في المفضلة بعد",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "اضغط على أيقونة الإشارة المرجعية بأعلى أي صفحة لحفظها هنا",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { Spacer(modifier = Modifier.height(10.dp)) }
                items(bookmarks) { item ->
                    Card(
                        onClick = {
                            when (item.itemType) {
                                "CATEGORY" -> {
                                    val id = item.itemId.removePrefix("cat_").toIntOrNull() ?: 1
                                    onNavigate(Screen.CategoryDetail(id))
                                }
                                "STAGE" -> {
                                    val rawId = item.itemId.removePrefix("stage_")
                                    val stage = repository.getStageById(item.itemId)
                                        ?: repository.getStageById(rawId)
                                        ?: repository.getStageById("stage_$rawId")
                                    val targetId = stage?.id ?: rawId
                                    onNavigate(Screen.StageDetail(targetId))
                                }
                                "MATERIAL" -> {
                                    val rawId = item.itemId.removePrefix("mat_")
                                    val mat = repository.getMaterialById(item.itemId)
                                        ?: repository.getMaterialById(rawId)
                                        ?: repository.getMaterialById("mat_$rawId")
                                    val targetId = mat?.id ?: rawId
                                    onNavigate(Screen.MaterialDetail(targetId))
                                }
                                "DEFECT" -> {
                                    val rawId = item.itemId.removePrefix("def_")
                                    val def = repository.getDefectById(item.itemId)
                                        ?: repository.getDefectById(rawId)
                                        ?: repository.getDefectById("def_$rawId")
                                    val targetId = def?.id ?: rawId
                                    onNavigate(Screen.DefectDetail(targetId))
                                }
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("bookmark_item_${item.id}")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${item.categoryTitle} • ${item.subtitle}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = FayrozTurquoise
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.toggleBookmark(
                                        id = item.id,
                                        type = item.itemType,
                                        title = item.title,
                                        subtitle = item.subtitle,
                                        categoryTitle = item.categoryTitle,
                                        isCurrently = true
                                    )
                                },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "إزالة",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}
