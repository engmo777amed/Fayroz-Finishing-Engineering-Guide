package com.example.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.FayrozTopBar
import com.example.ui.components.SectionHeader
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavy
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun StageDetailScreen(
    stageId: String,
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val repository = viewModel.repository
    val stage = repository.getStageById(stageId) ?: return
    val category = repository.getCategoryById(stage.categoryId)

    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val isBookmarked = bookmarkedIds.contains("stage_$stageId")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = stage.titleAr,
            subtitle = "المرحلة ${stage.stageNumber} • ${category?.titleAr ?: ""}",
            showBackButton = true,
            onBackClick = onBack,
            onSearchClick = { onNavigate(Screen.Search) },
            isBookmarked = isBookmarked,
            onToggleBookmark = {
                viewModel.toggleBookmark(
                    id = "stage_$stageId",
                    type = "STAGE",
                    title = stage.titleAr,
                    subtitle = "المرحلة رقم ${stage.stageNumber}",
                    categoryTitle = category?.titleAr ?: "",
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
                // Stage Objective & Action Button
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().testTag("stage_hero_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(FayrozGold),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${stage.stageNumber}",
                                    color = FayrozNavy,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "الهدف الهندسي من المرحلة:",
                                style = MaterialTheme.typography.titleSmall,
                                color = FayrozTurquoiseLight,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stage.objective,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = {
                                viewModel.startInspection(stage.id, stage.categoryId)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("stage_launch_inspection_btn"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = FayrozTurquoise)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = FayrozNavy
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "بدء استلام واستخراج تقرير فحص QA/QC",
                                color = FayrozNavy,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Prerequisites (الأعمال الواجب الانتهاء منها قبل البدء)
            item {
                DetailSectionCard(
                    title = "الأعمال الواجب الانتهاء منها قبل البدء",
                    icon = Icons.Default.CheckCircle,
                    accentColor = FayrozTurquoise
                ) {
                    Text(
                        text = stage.prerequisites,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Materials & Tools (المواد والخامات والأدوات المستخدمة)
            item {
                DetailSectionCard(
                    title = "المواد والخامات والأدوات المطلوبة",
                    icon = Icons.Default.Handyman,
                    accentColor = FayrozGold
                ) {
                    Text(
                        text = "الخامات الرئيسية:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    stage.requiredMaterials.forEach { mat ->
                        Text(
                            text = "• $mat",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Linked Materials from Database
                    val matchedMaterials = repository.allMaterials.filter { 
                        it.relatedStageIds.contains(stage.id) || it.categoryIds.contains(stage.categoryId) 
                    }.take(3)
                    if (matchedMaterials.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "خامات ومواصفات تفصيلية ذات صلة من الموسوعة:",
                            fontWeight = FontWeight.Bold,
                            color = FayrozTurquoise,
                            fontSize = 12.sp
                        )
                        matchedMaterials.forEach { mCard ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f))
                                    .clickable { onNavigate(Screen.MaterialDetail(mCard.id)) }
                                    .padding(horizontal = 10.dp, vertical = 8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Inventory2,
                                        contentDescription = null,
                                        tint = FayrozTurquoise,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = mCard.marketNameAr,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = mCard.egyptianBrands.take(3).joinToString(" • "),
                                            fontSize = 10.sp,
                                            color = FayrozTurquoiseLight
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "عرض",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "العدد والأدوات المستخدمة بالموقع:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    stage.toolsNeeded.forEach { tool ->
                        Text(
                            text = "• $tool",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Execution Steps (خطوات التنفيذ التفصيلية خطوة بخطوة)
            item {
                DetailSectionCard(
                    title = "خطوات التنفيذ التفصيلية خطوة بخطوة",
                    icon = Icons.Default.Construction,
                    accentColor = FayrozTurquoiseLight
                ) {
                    stage.executionSteps.forEach { step ->
                        Text(
                            text = step,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            // Dimensions & Slopes (المقاسات والمسافات والمناسيب والميول)
            item {
                DetailSectionCard(
                    title = "المقاسات والمناسيب والميول الهندسية",
                    icon = Icons.Default.Straighten,
                    accentColor = FayrozGold
                ) {
                    Text(
                        text = stage.dimensionsAndSlopes,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Mixes & Ratios (السماكات والخلطات والمعدلات)
            item {
                DetailSectionCard(
                    title = "الخلطات والنسب والمعدلات المسموح بها",
                    icon = Icons.Default.Science,
                    accentColor = FayrozTurquoise
                ) {
                    Text(
                        text = stage.mixesAndRatios,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // MEP Coordination (التنسيق مع الكهرباء والسباكة والتكييف)
            item {
                DetailSectionCard(
                    title = "التنسيق مع أعمال MEP (كهرباء وسباكة وتكييف)",
                    icon = Icons.Default.Inventory2,
                    accentColor = FayrozTurquoiseLight
                ) {
                    Text(
                        text = stage.mepCoordination,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Site Tips & Tricks (نصائح وتريكات التنفيذ من واقع المواقع)
            item {
                DetailSectionCard(
                    title = "نصائح وتريكات التنفيذ من واقع المواقع",
                    icon = Icons.Default.Lightbulb,
                    accentColor = FayrozGold
                ) {
                    stage.siteTips.forEach { tip ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(FayrozGold.copy(alpha = 0.1f))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = tip,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Common Mistakes (الأخطاء الشائعة وأسبابها)
            item {
                DetailSectionCard(
                    title = "الأخطاء الشائعة وأسبابها وتفاديها",
                    icon = Icons.Default.Warning,
                    accentColor = Color(0xFFEF4444)
                ) {
                    stage.commonMistakes.forEach { mistake ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFEF4444).copy(alpha = 0.1f))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "⚠️ $mistake",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            // Handover & Quality Tests (طريقة الاستلام ومعايير القبول والرفض)
            item {
                DetailSectionCard(
                    title = "اختبارات الجودة وطريقة الاستلام الهندسي",
                    icon = Icons.Default.Assignment,
                    accentColor = FayrozTurquoise
                ) {
                    Text(
                        text = "اختبارات الجودة:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    stage.qualityTests.forEach { test ->
                        Text(
                            text = "✓ $test",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "معايير القبول والرفض:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    stage.handoverCriteria.forEach { crit ->
                        Text(
                            text = "✓ $crit",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Code References (الأكواد والمراجع الهندسية)
            item {
                DetailSectionCard(
                    title = "المراجع والأكواد الهندسية المعتمدة",
                    icon = Icons.Default.MenuBook,
                    accentColor = FayrozNavyCard
                ) {
                    stage.codeReferences.forEach { code ->
                        Text(
                            text = "📜 $code",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun DetailSectionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    accentColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(accentColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}
