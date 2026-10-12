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
import com.example.ui.components.EditableText as Text
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
    val contentRevision = viewModel.repository.textStore.revision.collectAsState().value
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
                androidx.compose.material3.TextButton(onClick = { onNavigate(Screen.PracticalGuide(material.categoryIds.firstOrNull())) }, modifier = Modifier.fillMaxWidth()) {
                    Text("صور ودليل الاختيار والفحص", textKey = "MaterialDetailScreen.practical.guide")
                }
            }
            item {
                Spacer(modifier = Modifier.height(6.dp))
                // Market Identity Card
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().testTag("material_hero_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(textKey = "ui.MaterialDetailScreen.1",
                            text = "الاسم السوقي بمصر:",
                            fontSize = 11.sp,
                            color = FayrozTurquoiseLight,
                            fontWeight = FontWeight.Bold
                        )
                        Text(textKey = "ui.MaterialDetailScreen.2",
                            fieldKey = "material|${material.id}|marketNameAr", text = material.marketNameAr,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(textKey = "ui.MaterialDetailScreen.3",
                            fieldKey = "material|${material.id}|technicalNameAr", text = "الاسم العلمي/الفني: ${material.technicalNameAr}",
                            style = MaterialTheme.typography.bodySmall,
                            color = FayrozGold
                        )
                        if (material.tradeNames.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(textKey = "ui.MaterialDetailScreen.4",
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
                        material.egyptianBrands.forEachIndexed { editIndexEgyptianBrands, brand ->
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
                    Text(textKey = "ui.MaterialDetailScreen.5",
                        fieldKey = "material|${material.id}|definitionAndFunction", text = material.definitionAndFunction,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.6",
                        text = "الأنواع المتاحة: ${material.availableTypes.joinToString(" • ")}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.7",
                        text = "الاستخدامات: ${material.usages.joinToString(" • ")}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.8",
                        fieldKey = "material|${material.id}|prosAndCons", text = "المميزات والعيوب: ${material.prosAndCons}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(textKey = "ui.MaterialDetailScreen.9",
                        fieldKey = "material|${material.id}|componentsAndProperties", text = "المكونات والخصائص: ${material.componentsAndProperties}",
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
                    Text(textKey = "ui.MaterialDetailScreen.10",
                        text = "المقاسات والأبعاد:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    Text(textKey = "ui.MaterialDetailScreen.11",
                        fieldKey = "material|${material.id}|dimensionsAndSpecs", text = material.dimensionsAndSpecs,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.12",
                        fieldKey = "material|${material.id}|qualityGrades", text = "درجات الجودة: ${material.qualityGrades}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.13",
                        text = "المواصفات القياسية:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    Text(textKey = "ui.MaterialDetailScreen.14",
                        fieldKey = "material|${material.id}|standardSpecs", text = material.standardSpecs,
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
                        Text(textKey = "ui.MaterialDetailScreen.15",
                            fieldKey = "material|${material.id}|counterfeitDetection", text = material.counterfeitDetection,
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
                    Text(textKey = "ui.MaterialDetailScreen.16",
                        fieldKey = "material|${material.id}|installationMethod", text = material.installationMethod,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.17",
                        text = "الأدوات المطلوبة:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 13.sp
                    )
                    material.installationTools.forEachIndexed { editIndexInstallationTools, tool ->
                        Text(fieldKey = "material|${material.id}|installationTools|${editIndexInstallationTools}", textKey = "ui.MaterialDetailScreen.18",
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
                    Text(textKey = "ui.MaterialDetailScreen.19",
                        fieldKey = "material|${material.id}|storageAndHandling", text = material.storageAndHandling,
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
                    material.inspectionAndAcceptance.forEachIndexed { editIndexInspectionAndAcceptance, item ->
                        Text(fieldKey = "material|${material.id}|inspectionAndAcceptance|${editIndexInspectionAndAcceptance}", textKey = "ui.MaterialDetailScreen.20",
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
                    Text(textKey = "ui.MaterialDetailScreen.21",
                        fieldKey = "material|${material.id}|lifespan", text = "العمر الافتراضي التقديري: ${material.lifespan}",
                        fontWeight = FontWeight.Bold,
                        color = FayrozTurquoiseLight,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(textKey = "ui.MaterialDetailScreen.22",
                        fieldKey = "material|${material.id}|maintenanceInstructions", text = "تعليمات الصيانة: ${material.maintenanceInstructions}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(textKey = "ui.MaterialDetailScreen.23",
                        text = "أخطاء شائعة عند الاستخدام:",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF4444),
                        fontSize = 13.sp
                    )
                    material.commonMisuses.forEachIndexed { editIndexCommonMisuses, err ->
                        Text(fieldKey = "material|${material.id}|commonMisuses|${editIndexCommonMisuses}", textKey = "ui.MaterialDetailScreen.24",
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
                        material.relatedStageIds.forEachIndexed { editIndexRelatedStageIds, sId ->
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
                                    Text(textKey = "ui.MaterialDetailScreen.25",
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
