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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.example.ui.components.EditableText as Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.FinishingCategory
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.FayrozTopBar
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun CategoriesScreen(
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val contentRevision = viewModel.repository.textStore.revision.collectAsState().value
    val repository = viewModel.repository
    var filterText by rememberSaveable { mutableStateOf("") }

    val filteredList = remember(filterText) {
        if (filterText.isBlank()) {
            repository.allCategories
        } else {
            repository.allCategories.filter {
                it.titleAr.contains(filterText, ignoreCase = true) ||
                it.titleEn.contains(filterText, ignoreCase = true) ||
                it.summaryAr.contains(filterText, ignoreCase = true) ||
                it.code.contains(filterText, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "أقسام التشطيبات (30 قسماً)",
            subtitle = "تسلسل الأعمال التنفيذية بالموقع",
            showBackButton = true,
            onBackClick = onBack,
            onSearchClick = { onNavigate(Screen.Search) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = filterText,
                onValueChange = { filterText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("categories_search_input"),
                placeholder = { Text(textKey = "ui.CategoriesScreen.1", text = "تصفية الأقسام (مباني، محارة، سباكة، رخام، دهانات...)") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "بحث",
                        tint = FayrozTurquoise
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FayrozTurquoise,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize().testTag("categories_list"),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredList) { cat ->
                    val sCount = repository.getStagesByCategoryId(cat.id).size
                    val mCount = repository.getMaterialsByCategoryId(cat.id).size
                    val dCount = repository.getDefectsByCategoryId(cat.id).size
                    val cCount = repository.getChecklistItemsByCategoryId(cat.id).size

                    FullCategoryCard(
                        category = cat,
                        stagesCount = sCount,
                        materialsCount = mCount,
                        defectsCount = dCount,
                        checklistsCount = cCount,
                        onClick = { onNavigate(Screen.CategoryDetail(cat.id)) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun FullCategoryCard(
    category: FinishingCategory,
    stagesCount: Int = category.stagesCount,
    materialsCount: Int = category.materialsCount,
    defectsCount: Int = category.defectsCount,
    checklistsCount: Int = category.checklistsCount,
    onClick: () -> Unit
) {
    androidx.compose.runtime.CompositionLocalProvider(com.example.ui.components.LocalRecordScope provides "category|${category.id}") {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("cat_card_${category.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(FayrozNavyCard),
                    contentAlignment = Alignment.Center
                ) {
                    Text(textKey = "ui.CategoriesScreen.2",
                        fieldKey = "category|${category.id}|id", text = "${category.id}",
                        color = FayrozTurquoiseLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(FayrozGold.copy(alpha = 0.15f))
                                .padding(horizontal = 5.dp, vertical = 1.dp)
                        ) {
                            Text(textKey = "ui.CategoriesScreen.3",
                                text = category.code,
                                color = FayrozGold,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(textKey = "ui.CategoriesScreen.4",
                            fieldKey = "category|${category.id}|titleAr", text = category.titleAr,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Text(textKey = "ui.CategoriesScreen.5",
                        fieldKey = "category|${category.id}|titleEn", text = category.titleEn,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        maxLines = 1
                    )
                }
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "فتح",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(textKey = "ui.CategoriesScreen.6",
                fieldKey = "category|${category.id}|summaryAr", text = category.summaryAr,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SmallStatBadge("المراحل: $stagesCount", FayrozTurquoise)
                SmallStatBadge("الخامات: $materialsCount", FayrozGold)
                SmallStatBadge("العيوب: $defectsCount", Color(0xFFEF4444))
                SmallStatBadge("الفحص: $checklistsCount", FayrozTurquoiseLight)
            }
        }
    }

    }
}

@Composable
fun SmallStatBadge(text: String, color: Color) {
    Text(textKey = "ui.CategoriesScreen.7",
        text = text,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        color = color
    )
}
