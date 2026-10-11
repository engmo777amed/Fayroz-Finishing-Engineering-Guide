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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.FinishingViewModel
import com.example.ui.components.FayrozTopBar
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavy
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight

@Composable
fun CalculatorScreen(
    viewModel: FinishingViewModel,
    onBack: () -> Unit
) {
    val selectedType by viewModel.calcType.collectAsState()
    val areaInput by viewModel.calcAreaInput.collectAsState()
    val thicknessInput by viewModel.calcThicknessInput.collectAsState()
    val isPorcelain by viewModel.calcIsPorcelain.collectAsState()
    val isRedBrick by viewModel.calcIsRedBrick.collectAsState()

    val tabs = listOf("المحارة واللياسة", "السيراميك والبورسلين", "الدهانات والمعجون", "الجبس بورد", "المباني والطوب")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "حاسبة التشطيبات الهندسية",
            subtitle = "حساب معدلات استهلاك المواد وخامات الموقع",
            showBackButton = true,
            onBackClick = onBack
        )

        ScrollableTabRow(
            selectedTabIndex = selectedType,
            containerColor = FayrozNavy,
            contentColor = Color.White,
            edgePadding = 12.dp,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedType]),
                    color = FayrozTurquoise,
                    height = 3.dp
                )
            }
        ) {
            tabs.forEachIndexed { index, tabTitle ->
                Tab(
                    selected = selectedType == index,
                    onClick = { viewModel.calcType.value = index },
                    text = {
                        Text(
                            text = tabTitle,
                            fontSize = 12.sp,
                            fontWeight = if (selectedType == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedType == index) FayrozTurquoiseLight else Color.White.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.testTag("calc_tab_$index")
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                // Inputs Card
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "مدخلات الحساب بالموقع:",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = areaInput,
                            onValueChange = { viewModel.calcAreaInput.value = it },
                            label = { Text("المساحة المسطحة (م2)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().testTag("calc_area_input"),
                            singleLine = true
                        )

                        if (selectedType == 0) { // Plaster thickness
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = thicknessInput,
                                onValueChange = { viewModel.calcThicknessInput.value = it },
                                label = { Text("متوسط سمك المحارة (سم)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }

                        if (selectedType == 1) { // Tiles: Ceramic vs Porcelain
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                FilterChip(
                                    selected = isPorcelain,
                                    onClick = { viewModel.calcIsPorcelain.value = true },
                                    label = { Text("بورسلين (مادة C2TE)") }
                                )
                                FilterChip(
                                    selected = !isPorcelain,
                                    onClick = { viewModel.calcIsPorcelain.value = false },
                                    label = { Text("سيراميك عادي (مونة)") }
                                )
                            }
                        }

                        if (selectedType == 4) { // Masonry: Red brick vs Cement block
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                FilterChip(
                                    selected = isRedBrick,
                                    onClick = { viewModel.calcIsRedBrick.value = true },
                                    label = { Text("طوب أحمر مفرغ (25×12×6)") }
                                )
                                FilterChip(
                                    selected = !isRedBrick,
                                    onClick = { viewModel.calcIsRedBrick.value = false },
                                    label = { Text("بلوك أسمنتي (40×20×12)") }
                                )
                            }
                        }
                    }
                }
            }

            // Results Card
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().testTag("calc_results_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Calculate, contentDescription = null, tint = FayrozGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "الكميات والمعدلات التقديرية المطلوبة:",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        when (selectedType) {
                            0 -> { // Plaster
                                val res = viewModel.calculatePlasterResult()
                                CalcResultRow("الأسمنت المطلوب (شكارة 50 كجم):", "${res.cementBags50kg} شكارة", FayrozGold)
                                CalcResultRow("الرمل الحرش المغسول:", "${res.sandM3} م3", FayrozTurquoiseLight)
                                CalcResultRow("سائل أديبوند 65 للتماسك:", "${res.addibondKg} كجم", Color.White)
                                CalcResultRow("مياه الخلط التقريبية:", "${res.waterLiters.toInt()} لتر", Color.White)
                            }
                            1 -> { // Tiles
                                val res = viewModel.calculateTilesResult()
                                CalcResultRow("إجمالي البلاط المطلوب (شامل 7% هالك):", "${res.grossAreaM2} م2", FayrozGold)
                                CalcResultRow("رمل الدفان النظيف (فرشة 7 سم):", "${res.sandM3} م3", FayrozTurquoiseLight)
                                CalcResultRow("شكاير الأسمنت للمونة:", "${res.cementBags50kg} شكارة", Color.White)
                                CalcResultRow("مادة لاصقة C2TE (شكارة 25 كجم):", "${res.adhesiveBags25kg} شكارة", FayrozGold)
                                CalcResultRow("بودرة ملء الفواصل (السقية):", "${res.groutKg} كجم", Color.White)
                            }
                            2 -> { // Paint
                                val res = viewModel.calculatePaintResult()
                                CalcResultRow("سيلر مائي مقاوم للقلويات (بستلة 14 لتر):", "${res.sealerPails14L} بستلة", FayrozGold)
                                CalcResultRow("معجون أكريليك ناعم (شيكارة 20 كجم):", "${res.puttyBags20kg} شيكارة", FayrozTurquoiseLight)
                                CalcResultRow("دهان بلاستيك تشطيب (بستلة 14 لتر - وجهين):", "${res.finishPaintPails14L} بستلة", Color.White)
                            }
                            3 -> { // Gypsum
                                val res = viewModel.calculateGypsumResult()
                                CalcResultRow("ألواح جبس بورد 12.5 مم (1.2×2.4م):", "${res.boardsCount} لوح", FayrozGold)
                                CalcResultRow("قطاعات صاج أوميجا (طول 3م):", "${res.omegaProfiles} عود", FayrozTurquoiseLight)
                                CalcResultRow("قطاعات صاج سي رئيسية (طول 3م):", "${res.cProfiles} عود", Color.White)
                                CalcResultRow("زوايا محيطية (طول 3م):", "${res.angleProfiles} عود", Color.White)
                                CalcResultRow("تيش تعليق ملولب 6 مم:", "${res.threadedRods} قطعة", Color.White)
                                CalcResultRow("معجون فواصل كناوف (شكارة 25 كجم):", "${res.jointFillerBags25kg} شكارة", FayrozGold)
                                CalcResultRow("بكر شريط فايبر جلاس (لفة 90م):", "${res.fiberTapeRolls} بكرة", Color.White)
                            }
                            4 -> { // Masonry
                                val res = viewModel.calculateMasonryResult()
                                CalcResultRow("عدد القوالب المطلوبة (شامل 5% هالك):", "${res.blocksCount} قالب", FayrozGold)
                                CalcResultRow("الأسمنت المطلوب (شكارة 50 كجم):", "${res.cementBags50kg} شكارة", FayrozTurquoiseLight)
                                CalcResultRow("الرمل الحرش للمونة:", "${res.sandM3} م3", Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "💡 ملاحظة هندسية: المعدلات محسوبة بناءً على الكود المصري ومعدلات الهالك القياسية بالمشاريع وتعتبر دليلاً تقريبياً للاسترشاد والحصر الميداني.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CalcResultRow(label: String, value: String, valueColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color.White.copy(alpha = 0.9f), fontSize = 13.sp)
        Text(text = value, color = valueColor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
