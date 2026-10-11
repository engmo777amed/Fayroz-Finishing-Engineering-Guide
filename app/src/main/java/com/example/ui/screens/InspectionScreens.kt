package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.InspectionReportEntity
import com.example.model.InspectionCheckResult
import com.example.model.InspectionStatus
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.FayrozTopBar
import com.example.ui.components.StatusBadge
import com.example.ui.theme.FayrozGold
import com.example.ui.theme.FayrozNavy
import com.example.ui.theme.FayrozNavyCard
import com.example.ui.theme.FayrozTurquoise
import com.example.ui.theme.FayrozTurquoiseLight
import com.example.ui.theme.StatusFail
import com.example.ui.theme.StatusNA
import com.example.ui.theme.StatusPass
import com.example.ui.theme.StatusPending

@Composable
fun InspectionHubScreen(
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val reports by viewModel.allReports.collectAsState()
    val repository = viewModel.repository
    var showNewInspectionDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FayrozTopBar(
                title = "نظام الاستلامات الهندسية (QA/QC)",
                subtitle = "قوائم الفحص والتقارير الميدانية",
                showBackButton = true,
                onBackClick = onBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showNewInspectionDialog = true },
                containerColor = FayrozTurquoise,
                contentColor = FayrozNavyCard,
                modifier = Modifier.testTag("hub_new_inspection_fab")
            ) {
                Icon(Icons.Default.Add, contentDescription = "بدء استلام جديد")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                // QA Inspection Banner
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().height(140.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.banner_qa_inspection),
                            contentDescription = "استلامات الجودة بالموقع",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(FayrozNavyCard.copy(alpha = 0.8f))
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "تقارير الاستلام وضبط الجودة بالموقع",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "فحص هندسي مرحلي (قبل وأثناء وبعد التنفيذ) وفق معايير القبول والرفض الكودية.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "تقارير الاستلام المحفوظة بالموقع",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${reports.size} تقرير",
                        fontSize = 12.sp,
                        color = FayrozTurquoise,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (reports.isEmpty()) {
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Assignment,
                                contentDescription = null,
                                tint = FayrozTurquoise,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "لم تقم بتسجيل تقارير استلام بعد",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "اضغط على زر (+) بالأسفل لبدء أول استلام هندسي لأي بند",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(reports) { report ->
                    ReportCardItem(
                        report = report,
                        onView = { onNavigate(Screen.ViewReport(report.id)) },
                        onDelete = { viewModel.deleteReport(report.id) }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }

    if (showNewInspectionDialog) {
        AlertDialog(
            onDismissRequest = { showNewInspectionDialog = false },
            title = { Text("اختر البند لبدء الاستلام الهندسي", fontWeight = FontWeight.Bold) },
            text = {
                LazyColumn(modifier = Modifier.height(300.dp)) {
                    items(repository.allStages) { stage ->
                        val cat = repository.getCategoryById(stage.categoryId)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showNewInspectionDialog = false
                                    viewModel.startInspection(stage.id, stage.categoryId)
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Column {
                                Text(
                                    text = stage.titleAr,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${cat?.titleAr ?: ""} • المرحلة ${stage.stageNumber}",
                                    fontSize = 11.sp,
                                    color = FayrozTurquoiseLight
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showNewInspectionDialog = false }) {
                    Text("إلغاء")
                }
            }
        )
    }
}

@Composable
fun ReportCardItem(
    report: InspectionReportEntity,
    onView: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onView)
            .border(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
            .testTag("saved_report_${report.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = report.stageTitle,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "حذف",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Text(
                text = "${report.projectName} • ${report.unitOrZone}",
                style = MaterialTheme.typography.bodySmall,
                color = FayrozGold,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "مطابق: ${report.passedItems}", fontSize = 11.sp, color = StatusPass, fontWeight = FontWeight.Bold)
                Text(text = "غير مطابق: ${report.failedItems}", fontSize = 11.sp, color = StatusFail, fontWeight = FontWeight.Bold)
                Text(text = "لم يفحص: ${report.pendingItems}", fontSize = 11.sp, color = StatusPending, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = report.inspectionDate, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun ActiveInspectionScreen(
    stageId: String,
    categoryId: Int,
    viewModel: FinishingViewModel,
    onNavigate: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val repository = viewModel.repository
    val stage = repository.getStageById(stageId)
    val category = repository.getCategoryById(categoryId)

    val items by viewModel.activeInspectionItems.collectAsState()
    val projectName by viewModel.activeProjectName.collectAsState()
    val zoneName by viewModel.activeZoneName.collectAsState()
    val engineerName by viewModel.activeEngineerName.collectAsState()
    val generalNotes by viewModel.activeGeneralNotes.collectAsState()

    var showSaveDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "استلام: ${stage?.titleAr ?: "فحص البند"}",
            subtitle = category?.titleAr ?: "",
            showBackButton = true,
            onBackClick = onBack
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                // Project and Inspector metadata Card
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "بيانات الاستلام الميداني:",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = projectName,
                            onValueChange = { viewModel.setProjectName(it) },
                            label = { Text("اسم المشروع") },
                            modifier = Modifier.fillMaxWidth().testTag("input_project_name"),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = zoneName,
                                onValueChange = { viewModel.setZoneName(it) },
                                label = { Text("الوحدة / الفراغ") },
                                modifier = Modifier.weight(1f).testTag("input_zone_name"),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = engineerName,
                                onValueChange = { viewModel.setEngineerName(it) },
                                label = { Text("مهندس الاستلام") },
                                modifier = Modifier.weight(1f).testTag("input_engineer_name"),
                                singleLine = true
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "نقاط الفحص الكودية (${items.size} نقاط):",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = {
                            viewModel.saveCurrentReport(stageId, categoryId) { savedId ->
                                onNavigate(Screen.ViewReport(savedId))
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = FayrozTurquoise),
                        modifier = Modifier.testTag("save_inspection_btn")
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, tint = FayrozNavyCard, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("حفظ التقرير", color = FayrozNavyCard, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            items(items) { item ->
                CheckItemCard(
                    item = item,
                    onStatusChange = { newStatus ->
                        viewModel.updateCheckItemStatus(item.checkItemId, newStatus)
                    },
                    onNotesChange = { notes ->
                        viewModel.updateCheckItemNotes(item.checkItemId, notes)
                    }
                )
            }

            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "ملاحظات وتوصيات مهندس الموقع العامة:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = generalNotes,
                            onValueChange = { viewModel.setGeneralNotes(it) },
                            placeholder = { Text("سجل أي ملاحظات خاصة بالمهندس أو المقاول...") },
                            modifier = Modifier.fillMaxWidth().testTag("input_general_notes"),
                            minLines = 3
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CheckItemCard(
    item: InspectionCheckResult,
    onStatusChange: (InspectionStatus) -> Unit,
    onNotesChange: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .border(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f), RoundedCornerShape(10.dp))
            .testTag("check_item_${item.checkItemId}")
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(FayrozNavyCard)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = item.phaseTitle,
                        fontSize = 9.sp,
                        color = FayrozTurquoiseLight,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item.checkItemTitle,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "معيار القبول: ${item.criterion}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "أداة الفحص: ${item.toolUsed}",
                fontSize = 11.sp,
                color = FayrozGold
            )

            Spacer(modifier = Modifier.height(8.dp))
            // 4 Status Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                StatusButton(
                    title = "مطابق",
                    selected = item.status == InspectionStatus.PASSED,
                    color = StatusPass,
                    onClick = { onStatusChange(InspectionStatus.PASSED) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    title = "غير مطابق",
                    selected = item.status == InspectionStatus.FAILED,
                    color = StatusFail,
                    onClick = { onStatusChange(InspectionStatus.FAILED) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    title = "لم يفحص",
                    selected = item.status == InspectionStatus.PENDING,
                    color = StatusPending,
                    onClick = { onStatusChange(InspectionStatus.PENDING) },
                    modifier = Modifier.weight(1f)
                )
                StatusButton(
                    title = "لا ينطبق",
                    selected = item.status == InspectionStatus.NOT_APPLICABLE,
                    color = StatusNA,
                    onClick = { onStatusChange(InspectionStatus.NOT_APPLICABLE) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun StatusButton(
    title: String,
    selected: Boolean,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(if (selected) color else color.copy(alpha = 0.1f))
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color.White else color
        )
    }
}

@Composable
fun ViewReportScreen(
    reportId: Long,
    viewModel: FinishingViewModel,
    onBack: () -> Unit
) {
    val reports by viewModel.allReports.collectAsState()
    val report = reports.find { it.id == reportId }
    val context = LocalContext.current

    if (report == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("التقرير غير موجود")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FayrozTopBar(
            title = "تقرير استلام هندسي رسمي",
            subtitle = report.projectName,
            showBackButton = true,
            onBackClick = onBack
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                // Official Header
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = FayrozNavyCard),
                    modifier = Modifier.fillMaxWidth().testTag("official_report_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "FAYROZ ENGINEERING QA/QC REPORT",
                            fontSize = 11.sp,
                            color = FayrozGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "محضر استلام هندسي: ${report.stageTitle}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = "المشروع: ${report.projectName}", color = Color.White, fontSize = 12.sp)
                        Text(text = "الفراغ / الوحدة: ${report.unitOrZone}", color = Color.White, fontSize = 12.sp)
                        Text(text = "المهندس الفاحص: ${report.engineerName}", color = Color.White, fontSize = 12.sp)
                        Text(text = "تاريخ الاستلام: ${report.inspectionDate}", color = FayrozTurquoiseLight, fontSize = 12.sp)

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "مطابق: ${report.passedItems}", color = StatusPass, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Text(text = "غير مطابق: ${report.failedItems}", color = StatusFail, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Text(text = "لم يفحص: ${report.pendingItems}", color = StatusPending, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            }

            // Share / Export Action
            item {
                Button(
                    onClick = {
                        val shareText = buildString {
                            appendLine("═══════════════════════════════")
                            appendLine("تقرير استلام هندسي - FAYROZ QA/QC")
                            appendLine("═══════════════════════════════")
                            appendLine("المشروع: ${report.projectName}")
                            appendLine("الفراغ: ${report.unitOrZone}")
                            appendLine("البند: ${report.stageTitle}")
                            appendLine("المهندس: ${report.engineerName}")
                            appendLine("التاريخ: ${report.inspectionDate}")
                            appendLine("-------------------------------")
                            appendLine("ملخص الفحص: مطابق (${report.passedItems}) - غير مطابق (${report.failedItems})")
                            if (report.generalNotes.isNotBlank()) {
                                appendLine("ملاحظات: ${report.generalNotes}")
                            }
                            appendLine("═══════════════════════════════")
                        }
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, "تقرير استلام هندسي - ${report.stageTitle}")
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(Intent.createChooser(intent, "مشاركة تقرير الاستلام"))
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FayrozTurquoise),
                    modifier = Modifier.fillMaxWidth().testTag("share_report_btn")
                ) {
                    Icon(Icons.Default.Share, contentDescription = null, tint = FayrozNavyCard)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("مشاركة وتصدير التقرير الهندسي", color = FayrozNavyCard, fontWeight = FontWeight.Bold)
                }
            }

            // General Notes
            if (report.generalNotes.isNotBlank()) {
                item {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "ملاحظات الموقع المعتمدة:",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = report.generalNotes,
                                style = MaterialTheme.typography.bodyMedium
                            )
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
