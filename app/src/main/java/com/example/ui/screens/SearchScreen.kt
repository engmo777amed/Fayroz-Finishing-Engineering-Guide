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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.example.ui.components.EditableText as Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.EgyptianBrandChip
import com.example.ui.components.FayrozTopBar
import com.example.ui.components.SectionHeader
import com.example.ui.components.SeverityBadge
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun SearchScreen(
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val query by viewModel.searchQuery.collectAsState()
    val results by viewModel.searchResults.collectAsState()

    val suggestedKeywords = listOf(
        "بؤجة", "تطبيل", "PPR", "سيلر", "علبة ماجيك", "شرب", "قدة",
        "السويدي", "عزل 4 مم", "كناوف", "جبس بورد", "بورسلين", "جامبو"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "البحث الهندسي الذكي",
            subtitle = "بحث فوري بالمصطلحات السوقية والفنية",
            showBackButton = true,
            onBackClick = onBack
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = query,
                onValueChange = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("global_search_input"),
                placeholder = { Text(textKey = "ui.SearchScreen.1", text = "ابحث عن أي بند، خامة، مقاس، أو عيب...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = FayrozTurquoise)
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "مسح")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FayrozTurquoise,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Suggestions Chips
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(suggestedKeywords) { keyword ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(FayrozNavyCard)
                            .clickable { viewModel.updateSearchQuery(keyword) }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(textKey = "ui.SearchScreen.2",
                            text = keyword,
                            fontSize = 11.sp,
                            color = FayrozTurquoiseLight,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (query.isBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(textKey = "ui.SearchScreen.3",
                        text = "اكتب كلمة للبحث في مراحل التنفيذ والخامات والعيوب والمقاسات",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else if (results.totalCount == 0) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(textKey = "ui.SearchScreen.4",
                        text = "لا توجد نتائج مطابقة لكلمة \"$query\"، جرب مرادفات أخرى.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Categories Results
                    if (results.categories.isNotEmpty()) {
                        item { SectionHeader("أقسام وبنود التشطيبات", count = results.categories.size) }
                        items(results.categories) { cat ->
                            Card(
                                onClick = { onNavigate(Screen.CategoryDetail(cat.id)) },
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(FayrozNavyCard),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(textKey = "ui.SearchScreen.5",
                                            text = "${cat.id}",
                                            color = FayrozTurquoiseLight,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(textKey = "ui.SearchScreen.6",
                                            text = "${cat.code} • ${cat.titleAr}",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(textKey = "ui.SearchScreen.7",
                                            text = cat.summaryAr,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "عرض",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Stages Results
                    if (results.stages.isNotEmpty()) {
                        item { SectionHeader("مراحل التنفيذ", count = results.stages.size) }
                        items(results.stages) { stage ->
                            Card(
                                onClick = { onNavigate(Screen.StageDetail(stage.id)) },
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(textKey = "ui.SearchScreen.8",
                                        text = stage.titleAr,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(textKey = "ui.SearchScreen.9",
                                        text = stage.objective,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }

                    // Materials Results
                    if (results.materials.isNotEmpty()) {
                        item { SectionHeader("مكتبة الخامات والمواد", count = results.materials.size) }
                        items(results.materials) { mat ->
                            Card(
                                onClick = { onNavigate(Screen.MaterialDetail(mat.id)) },
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(textKey = "ui.SearchScreen.10",
                                        text = mat.marketNameAr,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(textKey = "ui.SearchScreen.11",
                                        text = "${mat.technicalNameAr} • ${mat.egyptianBrands.joinToString(", ")}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = FayrozTurquoiseLight
                                    )
                                }
                            }
                        }
                    }

                    // Defects Results
                    if (results.defects.isNotEmpty()) {
                        item { SectionHeader("المشاكل والعيوب", count = results.defects.size) }
                        items(results.defects) { def ->
                            Card(
                                onClick = { onNavigate(Screen.DefectDetail(def.id)) },
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(textKey = "ui.SearchScreen.12",
                                            text = def.titleAr,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(textKey = "ui.SearchScreen.13",
                                            text = def.marketName,
                                            fontSize = 11.sp,
                                            color = FayrozGold
                                        )
                                    }
                                    SeverityBadge(def.severity)
                                }
                            }
                        }
                    }

                    // Dimensions Results
                    if (results.dimensions.isNotEmpty()) {
                        item { SectionHeader("المقاسات والمناسيب", count = results.dimensions.size) }
                        items(results.dimensions) { dim ->
                            Card(
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(textKey = "ui.SearchScreen.14",
                                            text = dim.itemTitle,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(textKey = "ui.SearchScreen.15",
                                            text = dim.referenceSource,
                                            fontSize = 11.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(FayrozGold.copy(alpha = 0.15f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(textKey = "ui.SearchScreen.16",
                                            text = dim.standardValue,
                                            fontWeight = FontWeight.Bold,
                                            color = FayrozGold,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(20.dp)) }
                }
            }
        }
    }
}
