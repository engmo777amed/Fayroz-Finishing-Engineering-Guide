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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.model.TroubleshootingDefect
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.FayrozTopBar
import com.example.ui.components.SeverityBadge
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun TroubleshootingListScreen(
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val repository = viewModel.repository
    var query by remember { mutableStateOf("") }

    val filteredDefects = remember(query) {
        if (query.isBlank()) {
            repository.allDefects
        } else {
            repository.allDefects.filter {
                it.titleAr.contains(query, ignoreCase = true) ||
                it.marketName.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "المشاكل والعيوب وعلاجها",
            subtitle = "دليل تشخيص وإصلاح العيوب التنفيذية بالموقع",
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
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("defects_search_input"),
                placeholder = { Text("ابحث عن عيب (تطبيل، شروخ، تقشير، تسريب...)") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "بحث",
                        tint = FayrozTurquoise
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
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

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredDefects) { defect ->
                    Card(
                        onClick = { onNavigate(Screen.DefectDetail(defect.id)) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("defect_item_${defect.id}")
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = defect.titleAr,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.weight(1f)
                                )
                                SeverityBadge(defect.severity)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "المصطلح الدارج: ${defect.marketName}",
                                style = MaterialTheme.typography.labelSmall,
                                color = FayrozGold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = defect.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "عرض التشخيص الهندسي وخطوات الإصلاح ←",
                                    color = FayrozTurquoise,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    imageVector = Icons.Default.ChevronLeft,
                                    contentDescription = null,
                                    tint = FayrozTurquoise
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun DefectDetailScreen(
    defectId: String,
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val repository = viewModel.repository
    val defect = repository.getDefectById(defectId) ?: return

    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val isBookmarked = bookmarkedIds.contains("def_$defectId")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = defect.titleAr,
            subtitle = defect.marketName,
            showBackButton = true,
            onBackClick = onBack,
            onSearchClick = { onNavigate(Screen.Search) },
            isBookmarked = isBookmarked,
            onToggleBookmark = {
                viewModel.toggleBookmark(
                    id = "def_$defectId",
                    type = "DEFECT",
                    title = defect.titleAr,
                    subtitle = defect.marketName,
                    categoryTitle = "المشاكل والعيوب",
                    isCurrently = isBookmarked
                )
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                // Defect Header Card
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().testTag("defect_hero_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = defect.titleAr,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            SeverityBadge(defect.severity)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "الاسم بالموقع: ${defect.marketName}",
                            style = MaterialTheme.typography.bodySmall,
                            color = FayrozGold,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = defect.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            // Detection Signs (علامات الاكتشاف)
            item {
                DetailSectionCard(
                    title = "العلامات التي تساعد على اكتشاف العيب بالموقع",
                    icon = Icons.Default.Visibility,
                    accentColor = FayrozTurquoise
                ) {
                    defect.detectionSigns.forEach { sign ->
                        Text(
                            text = "👁 $sign",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                }
            }

            // Probable Causes (الأسباب المحتملة)
            item {
                DetailSectionCard(
                    title = "الأسباب المحتملة لظهور المشكلة",
                    icon = Icons.Default.HelpOutline,
                    accentColor = Color(0xFFEF4444)
                ) {
                    defect.probableCauses.forEach { cause ->
                        Text(
                            text = "• $cause",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                }
            }

            // Root Cause Diagnosis (طريقة تحديد السبب الحقيقي)
            item {
                DetailSectionCard(
                    title = "طريقة تحديد السبب الجذري الحقيقي",
                    icon = Icons.Default.Info,
                    accentColor = FayrozGold
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(FayrozGold.copy(alpha = 0.12f))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = defect.rootCauseDiagnosis,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Repair Steps (خطوات الإصلاح بالتفصيل)
            item {
                DetailSectionCard(
                    title = "خطوات الإصلاح والمعالجة الهندسية بالتفصيل",
                    icon = Icons.Default.Build,
                    accentColor = FayrozTurquoiseLight
                ) {
                    defect.repairSteps.forEach { step ->
                        Text(
                            text = step,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "الخامات المستخدمة في المعالجة والإصلاح:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    defect.repairMaterials.forEach { mat ->
                        Text(
                            text = "🔧 $mat",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // Prevention Method (طريقة منع تكرار المشكلة)
            item {
                DetailSectionCard(
                    title = "طريقة تفادي ومنع تكرار المشكلة مستقبلاً",
                    icon = Icons.Default.Shield,
                    accentColor = FayrozTurquoise
                ) {
                    Text(
                        text = defect.preventionMethod,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Handover Retest (طريقة اختبار واستلام الإصلاح)
            item {
                DetailSectionCard(
                    title = "طريقة اختبار واستلام أعمال الإصلاح",
                    icon = Icons.Default.CheckCircle,
                    accentColor = FayrozGold
                ) {
                    Text(
                        text = defect.handoverRetest,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
