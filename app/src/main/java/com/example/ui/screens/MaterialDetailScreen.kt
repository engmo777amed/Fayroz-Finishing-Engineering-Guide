package com.example.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
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
import com.example.ui.components.EgyptianBrandChip
import com.example.ui.components.FayrozTopBar
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MaterialDetailScreen(
    materialId: String,
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val repository = viewModel.repository
    val material = repository.getMaterialById(materialId) ?: return

    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val isBookmarked = bookmarkedIds.contains("mat_$materialId")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = material.marketNameAr,
            subtitle = material.classification,
            showBackButton = true,
            onBackClick = onBack,
            onSearchClick = { onNavigate(Screen.Search) },
            isBookmarked = isBookmarked,
            onToggleBookmark = {
                viewModel.toggleBookmark(
                    id = "mat_$materialId",
                    type = "MATERIAL",
                    title = material.marketNameAr,
                    subtitle = material.classification,
                    categoryTitle = "مكتبة الخامات",
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
                // Market Identity Card
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().testTag("material_hero_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "الاسم السوقي بمصر:",
                            fontSize = 11.sp,
                            color = FayrozTurquoiseLight,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = material.marketNameAr,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "الاسم العلمي/الفني: ${material.technicalNameAr}",
                            style = MaterialTheme.typography.bodySmall,
                            color = FayrozGold
                        )
                        if (material.tradeNames.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "الأسماء التجارية: ${material.tradeNames.joinToString(" • ")}",
                                fontSize = 11.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            // Brands in Egypt (العلامات التجارية المتاحة في مصر)
            item {
                DetailSectionCard(
                    title = "العلامات التجارية والشركات في السوق المصري",
                    icon = Icons.Default.Business,
                    accentColor = FayrozTurquoise
                ) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        material.egyptianBrands.forEach { brand ->
                            EgyptianBrandChip(brand)
                        }
                    }
                }
            }

            // Definition & Function (التعريف والوظيفة الهندسية)
            item {
                DetailSectionCard(
                    title = "التعريف والوظيفة الهندسية",
                    icon = Icons.Default.Info,
                    accentColor = FayrozTurquoiseLight
                ) {
                    Text(
                        text = material.definitionAndFunction,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "المكونات والخصائص: ${material.componentsAndProperties}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Dimensions & Specifications (المقاسات والمواصفات القياسية)
            item {
                DetailSectionCard(
                    title = "المقاسات والأقطار والمواصفات القياسية",
                    icon = Icons.Default.Rule,
                    accentColor = FayrozGold
                ) {
                    Text(
                        text = "المقاسات والأبعاد:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    Text(
                        text = material.dimensionsAndSpecs,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "المواصفات القياسية:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    Text(
                        text = material.standardSpecs,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Counterfeit Detection (CRITICAL: كشف الغش التجاري والخامة الرديئة)
            item {
                DetailSectionCard(
                    title = "كشف الخامة الرديئة والغش التجاري بالموقع",
                    icon = Icons.Default.Security,
                    accentColor = Color(0xFFEF4444)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFEF4444).copy(alpha = 0.1f))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = material.counterfeitDetection,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Installation Method & Tools (طريقة التركيب والأدوات)
            item {
                DetailSectionCard(
                    title = "طريقة التركيب والاستخدام والأدوات",
                    icon = Icons.Default.Construction,
                    accentColor = FayrozTurquoise
                ) {
                    Text(
                        text = material.installationMethod,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "الأدوات المطلوبة:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    material.installationTools.forEach { tool ->
                        Text(
                            text = "• $tool",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Storage & Handling (شروط التخزين والنقل)
            item {
                DetailSectionCard(
                    title = "شروط التخزين والنقل والتداول بالموقع",
                    icon = Icons.Default.Inventory,
                    accentColor = FayrozGold
                ) {
                    Text(
                        text = material.storageAndHandling,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Inspection & Acceptance (اختبارات فحص واستلام الخامة)
            item {
                DetailSectionCard(
                    title = "اختبارات فحص واستلام الخامة",
                    icon = Icons.Default.CheckCircle,
                    accentColor = FayrozTurquoiseLight
                ) {
                    material.inspectionAndAcceptance.forEach { item ->
                        Text(
                            text = "✓ $item",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 3.dp)
                        )
                    }
                }
            }

            // Common Misuses & Lifespan (الأخطاء الشائعة والعمر الافتراضي)
            item {
                DetailSectionCard(
                    title = "الأخطاء الشائعة والعمر الافتراضي والصيانة",
                    icon = Icons.Default.HourglassTop,
                    accentColor = FayrozGold
                ) {
                    Text(
                        text = "العمر الافتراضي التقديري: ${material.lifespan}",
                        fontWeight = FontWeight.Bold,
                        color = FayrozTurquoiseLight,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "تعليمات الصيانة: ${material.maintenanceInstructions}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "أخطاء شائعة عند الاستخدام:",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF4444),
                        fontSize = 13.sp
                    )
                    material.commonMisuses.forEach { err ->
                        Text(
                            text = "• $err",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Related Execution Stages Links (الانتقال لمراحل التنفيذ التي تستخدم فيها الخامة)
            if (material.relatedStageIds.isNotEmpty()) {
                item {
                    DetailSectionCard(
                        title = "مراحل التنفيذ التي تستخدم هذه الخامة",
                        icon = Icons.Default.PlayArrow,
                        accentColor = FayrozTurquoise
                    ) {
                        material.relatedStageIds.forEach { sId ->
                            val linkedStage = repository.getStageById(sId)
                            if (linkedStage != null) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                                        .clickable { onNavigate(Screen.StageDetail(linkedStage.id)) }
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = "المرحلة ${linkedStage.stageNumber}: ${linkedStage.titleAr} ←",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
