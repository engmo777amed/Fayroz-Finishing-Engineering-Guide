package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.ui.components.EditableText as Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
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
import com.example.model.ExecutionStage
import com.example.model.MaterialCard
import com.example.model.QualityCheckItem
import com.example.model.TechnicalDimension
import com.example.model.TroubleshootingDefect
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.EgyptianBrandChip
import com.example.ui.components.FayrozTopBar
import com.example.ui.components.SeverityBadge
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavy
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun CategoryDetailScreen(
    categoryId: Int,
    initialTab: Int = 0,
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val contentRevision = viewModel.repository.textStore.revision.collectAsState().value
    val repository = viewModel.repository
    val category = repository.getCategoryById(categoryId) ?: return

    var selectedTab by rememberSaveable(categoryId) { mutableIntStateOf(initialTab) }
    val tabStates = rememberSaveableStateHolder()
    val stages = remember(contentRevision, categoryId) { repository.getStagesByCategoryId(categoryId) }
    val materials = remember(contentRevision, categoryId) { repository.getMaterialsByCategoryId(categoryId) }
    val checkItems = remember(contentRevision, categoryId) { repository.getChecklistItemsByCategoryId(categoryId) }
    val defects = remember(contentRevision, categoryId) { repository.getDefectsByCategoryId(categoryId) }
    val dimensions = remember(contentRevision, categoryId) { repository.getDimensionsByCategoryId(categoryId) }

    val tabs = remember(stages.size, materials.size, checkItems.size, defects.size, dimensions.size) {
        listOf(
            "مراحل التنفيذ (${stages.size})",
            "مكتبة الخامات (${materials.size})",
            "الاستلامات (${checkItems.size})",
            "المشاكل والحلول (${defects.size})",
            "المقاسات والاشتراطات (${dimensions.size})"
        )
    }

    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val isBookmarked = bookmarkedIds.contains("cat_$categoryId")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = category.titleAr,
            subtitle = "${category.code} • ${category.titleEn}",
            showBackButton = true,
            onBackClick = onBack,
            onSearchClick = { onNavigate(Screen.Search) },
            isBookmarked = isBookmarked,
            onToggleBookmark = {
                viewModel.toggleBookmark(
                    id = "cat_$categoryId",
                    type = "CATEGORY",
                    title = category.titleAr,
                    subtitle = category.code,
                    categoryTitle = category.titleAr,
                    isCurrently = isBookmarked
                )
            }
        )

        androidx.compose.material3.TextButton(onClick = { onNavigate(Screen.PracticalGuide(categoryId)) }, modifier = Modifier.fillMaxWidth().testTag("category_practical_guide")) {
            Text("صور وشرح عملي لهذا البند", textKey = "category.practical.guide")
        }

        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = FayrozNavy,
            contentColor = Color.White,
            edgePadding = 12.dp,
            indicator = { tabPositions ->
                if (selectedTab in tabPositions.indices) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = FayrozTurquoise,
                        height = 3.dp
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(textKey = "ui.CategoryDetailScreen.tab.$index",
                            text = tabTitle,
                            fontSize = 12.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == index) FayrozTurquoiseLight else Color.White.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.testTag("cat_tab_$index")
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            tabStates.SaveableStateProvider(selectedTab) {
            when (selectedTab) {
                0 -> StagesTabContent(
                    stages = stages,
                    onStageClick = { onNavigate(Screen.StageDetail(it.id)) }
                )
                1 -> MaterialsTabContent(
                    materials = materials,
                    onMaterialClick = { onNavigate(Screen.MaterialDetail(it.id)) }
                )
                2 -> InspectionsTabContent(
                    checkItems = checkItems
                )
                3 -> DefectsTabContent(
                    defects = defects,
                    onDefectClick = { onNavigate(Screen.DefectDetail(it.id)) }
                )
                4 -> DimensionsTabContent(
                    dimensions = dimensions
                )
            }
            }
        }
    }
}

@Composable
fun StagesTabContent(
    stages: List<ExecutionStage>,
    onStageClick: (ExecutionStage) -> Unit
) {
    if (stages.isEmpty()) {
        EmptyTabNotice("جاري إدراج مراحل تفصيلية إضافية لهذا البند...")
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(textKey = "ui.CategoryDetailScreen.2",
                text = "مراحل التنفيذ الميدانية؛ اختَر ما ينطبق على النظام المعتمد واتبع الأعمال السابقة لكل مرحلة:",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        items(stages) { stage ->
            StageCardItem(
                stage = stage,
                onClick = { onStageClick(stage) }
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StageCardItem(
    stage: ExecutionStage,
    onClick: () -> Unit
) {
    androidx.compose.runtime.CompositionLocalProvider(com.example.ui.components.LocalRecordScope provides "stage|${stage.id}") {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("stage_card_${stage.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(FayrozTurquoise.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(textKey = "ui.CategoryDetailScreen.3",
                        fieldKey = "stage|${stage.id}|stageNumber", text = "${stage.stageNumber}",
                        color = FayrozTurquoise,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(textKey = "ui.CategoryDetailScreen.4",
                    fieldKey = "stage|${stage.id}|titleAr", text = stage.titleAr,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(textKey = "ui.CategoryDetailScreen.5",
                fieldKey = "stage|${stage.id}|objective", text = stage.objective,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(textKey = "ui.CategoryDetailScreen.6",
                    text = "عرض التفاصيل الهندسية (${stage.executionSteps.size} خطوات) ←",
                    color = FayrozTurquoise,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }

    }
}

@Composable
fun MaterialsTabContent(
    materials: List<MaterialCard>,
    onMaterialClick: (MaterialCard) -> Unit
) {
    if (materials.isEmpty()) {
        EmptyTabNotice("جاري استكمال بطاقات الخامات الإضافية لهذا القسم...")
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(materials) { mat ->
            MaterialCardItem(
                material = mat,
                onClick = { onMaterialClick(mat) }
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MaterialCardItem(
    material: MaterialCard,
    onClick: () -> Unit
) {
    androidx.compose.runtime.CompositionLocalProvider(com.example.ui.components.LocalRecordScope provides "material|${material.id}") {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("mat_card_${material.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(textKey = "ui.CategoryDetailScreen.7",
                        fieldKey = "material|${material.id}|marketNameAr", text = material.marketNameAr,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(textKey = "ui.CategoryDetailScreen.8",
                        fieldKey = "material|${material.id}|technicalNameAr", text = material.technicalNameAr,
                        style = MaterialTheme.typography.labelSmall,
                        color = FayrozTurquoiseLight
                    )
                }
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "عرض",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(textKey = "ui.CategoryDetailScreen.9",
                fieldKey = "material|${material.id}|definitionAndFunction", text = material.definitionAndFunction,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                material.egyptianBrands.take(4).forEach { brand ->
                    EgyptianBrandChip(brand)
                }
            }
        }
    }

    }
}

@Composable
fun InspectionsTabContent(
    checkItems: List<QualityCheckItem>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(checkItems) { item ->
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(FayrozGold.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(textKey = "ui.CategoryDetailScreen.10",
                                text = item.phase.titleAr,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = FayrozGold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(textKey = "ui.CategoryDetailScreen.11",
                            fieldKey = "check|${item.id}|titleAr", text = item.titleAr,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(textKey = "ui.CategoryDetailScreen.12",
                        fieldKey = "check|${item.id}|method", text = "طريقة الفحص: ${item.method}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(textKey = "ui.CategoryDetailScreen.13",
                        fieldKey = "check|${item.id}|acceptanceCriterion", text = "معيار القبول: ${item.acceptanceCriterion}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(fieldKey = "check|${item.id}|toolUsed", textKey = "check.tool", text = "أداة الفحص: ${item.toolUsed}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    com.example.ui.components.ReferenceText(text = item.technicalReference, fieldKey = "check|${item.id}|technicalReference", textKey = "check.reference")
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DefectsTabContent(
    defects: List<TroubleshootingDefect>,
    onDefectClick: (TroubleshootingDefect) -> Unit
) {
    if (defects.isEmpty()) {
        EmptyTabNotice("لا توجد بلاغات عيوب مسجلة لهذا القسم حالياً.")
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(defects) { defect ->
            Card(
                onClick = { onDefectClick(defect) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(textKey = "ui.CategoryDetailScreen.15",
                            fieldKey = "defect|${defect.id}|titleAr", text = defect.titleAr,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        SeverityBadge(defect.severity)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(textKey = "ui.CategoryDetailScreen.16",
                        fieldKey = "defect|${defect.id}|marketName", text = "الاسم الدارج: ${defect.marketName}",
                        style = MaterialTheme.typography.labelSmall,
                        color = FayrozGold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(textKey = "ui.CategoryDetailScreen.17",
                        fieldKey = "defect|${defect.id}|description", text = defect.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.CategoryDetailScreen.18",
                        text = "طريقة العلاج: ${defect.repairSteps.firstOrNull() ?: ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DimensionsTabContent(
    dimensions: List<TechnicalDimension>
) {
    if (dimensions.isEmpty()) {
        EmptyTabNotice("جاري إدراج المقاسات والمناسيب الكودية لهذا القسم...")
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(dimensions) { dim ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(textKey = "ui.CategoryDetailScreen.19",
                            fieldKey = "dimension|${dim.id}|itemTitle", text = dim.itemTitle,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(FayrozGold.copy(alpha = 0.15f))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(textKey = "ui.CategoryDetailScreen.20",
                                fieldKey = "dimension|${dim.id}|standardValue", text = dim.standardValue,
                                color = FayrozGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(textKey = "ui.CategoryDetailScreen.21",
                        fieldKey = "dimension|${dim.id}|applicationCondition", text = "شرط التطبيق: ${dim.applicationCondition}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(textKey = "ui.CategoryDetailScreen.22",
                        fieldKey = "dimension|${dim.id}|tolerance", text = "التفاوت المسموح: ${dim.tolerance}",
                        fontSize = 11.sp,
                        color = FayrozTurquoiseLight
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(textKey = "ui.CategoryDetailScreen.23",
                        fieldKey = "dimension|${dim.id}|referenceSource", text = "المرجع: ${dim.referenceSource}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EmptyTabNotice(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(textKey = "ui.CategoryDetailScreen.24",
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
