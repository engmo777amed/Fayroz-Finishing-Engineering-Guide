package com.example.ui.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.*
import com.example.ui.FinishingViewModel
import com.example.ui.Screen
import com.example.ui.components.*
import com.example.ui.components.EditableText as Text

@Composable
fun PracticalGuideScreen(categoryId: Int?, viewModel: FinishingViewModel, onNavigate: (Screen) -> Unit, initialQuery: String = "", onBack: () -> Unit) {
    val store = viewModel.repository.textStore
    val revision = store.revision.collectAsState().value
    var query by remember { mutableStateOf(initialQuery) }
    val entries = remember(categoryId, query, revision) {
        PracticalGuideData.entries.filter { categoryId == null || it.categoryId == categoryId }.map { store.apply(it) }
            .filter { query.isBlank() || it.title.contains(query, true) || it.body.contains(query, true) }
    }
    val figures = remember(categoryId, query, revision) {
        TechnicalIllustrations.figures.filter { categoryId == null || categoryId in it.categoryIds }.map { store.apply(it) }
            .filter { query.isBlank() || it.title.contains(query, true) || it.caption.contains(query, true) || it.labels.any { label -> label.contains(query, true) } }
    }
    Column(Modifier.fillMaxSize()) {
        FayrozTopBar(title = "الصور والدليل العملي", subtitle = categoryId?.let { viewModel.repository.getCategoryById(it)?.titleAr }, showBackButton = true, onBackClick = onBack)
        OutlinedTextField(value = query, onValueChange = { query = it }, label = { Text("بحث في الصور والشرح", textKey = "guide.search") }, singleLine = true, modifier = Modifier.fillMaxWidth().padding(12.dp))
        LazyColumn(Modifier.weight(1f).testTag("practical_guide_list"), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { Text("اضغط على الصورة لتكبيرها. اضغط مطولًا على أي نص لتعديله؛ يُحفظ التعديل على جهازك.", textKey = "guide.instructions") }
            items(figures, key = { "figure:${it.id}" }) { FigureCard(it) }
            items(entries, key = { "guide:${it.id}" }) { entry ->
                CompositionLocalProvider(LocalRecordScope provides "guide|${entry.id}") {
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(fieldKey = "guide|${entry.id}|title", text = entry.title, style = MaterialTheme.typography.titleMedium)
                            Text(fieldKey = "guide|${entry.id}|body", text = entry.body, style = MaterialTheme.typography.bodyMedium)
                            ReferenceText(text = entry.source, fieldKey = "guide|${entry.id}|source", textKey = "guide.source")
                            if (categoryId == null) TextButton(onClick = { onNavigate(Screen.CategoryDetail(entry.categoryId)) }) { Text("فتح البند", textKey = "guide.open.category") }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FigureCard(figure: TechnicalFigure) {
    var expanded by remember(figure.id) { mutableStateOf(false) }
    CompositionLocalProvider(LocalRecordScope provides "figure|${figure.id}") {
        Card(Modifier.fillMaxWidth().testTag("figure_${figure.id}")) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(fieldKey = "figure|${figure.id}|title", text = figure.title, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp), style = MaterialTheme.typography.titleMedium)
                FigureImage(figure, Modifier.fillMaxWidth().aspectRatio(4f / 3f).testTag("figure_image_${figure.id}").clickable { expanded = true })
                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    figure.labels.forEachIndexed { index, label -> Text(text = label, fieldKey = "figure|${figure.id}|labels|$index", style = MaterialTheme.typography.bodyMedium) }
                    Text(fieldKey = "figure|${figure.id}|caption", text = figure.caption, style = MaterialTheme.typography.bodyMedium)
                    Text(fieldKey = "figure|${figure.id}|limits", text = figure.limits, style = MaterialTheme.typography.bodySmall)
                    ReferenceText(text = figure.sourceUrl, fieldKey = "figure|${figure.id}|sourceUrl", textKey = "figure.source")
                }
            }
        }
        if (expanded) {
            Dialog(onDismissRequest = { expanded = false }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
                var scale by remember { mutableFloatStateOf(1f) }
                var offsetX by remember { mutableFloatStateOf(0f) }
                var offsetY by remember { mutableFloatStateOf(0f) }
                Surface(Modifier.fillMaxSize()) {
                    Column {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            TextButton(onClick = { expanded = false }) { Text("إغلاق", textKey = "figure.close") }
                            TextButton(onClick = { scale = 1f; offsetX = 0f; offsetY = 0f }) { Text("إعادة العرض", textKey = "figure.reset") }
                        }
                        Box(Modifier.weight(1f).fillMaxWidth().pointerInput(figure.id) {
                            detectTransformGestures { _, pan, zoom, _ ->
                                scale = (scale * zoom).coerceIn(1f, 4f)
                                offsetX = (offsetX + pan.x).coerceIn(-size.width * (scale - 1f), size.width * (scale - 1f))
                                offsetY = (offsetY + pan.y).coerceIn(-size.height * (scale - 1f), size.height * (scale - 1f))
                            }
                        }, contentAlignment = Alignment.Center) {
                            FigureImage(figure, Modifier.fillMaxWidth().aspectRatio(4f / 3f).graphicsLayer { scaleX = scale; scaleY = scale; translationX = offsetX; translationY = offsetY })
                        }
                        Text(fieldKey = "figure|${figure.id}|limits", text = figure.limits, modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Composable
private fun FigureImage(figure: TechnicalFigure, modifier: Modifier) {
    val context = LocalContext.current
    val bitmap = remember(figure.assetName) { context.assets.open("figures/${figure.assetName}").use { BitmapFactory.decodeStream(it) }.asImageBitmap() }
    Box(modifier.background(Color(0xFFEAE8E3))) {
        Image(bitmap, contentDescription = figure.title, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize())
        Surface(Modifier.align(Alignment.BottomCenter).fillMaxWidth(), color = Color.White.copy(alpha = 0.94f)) {
            Text(fieldKey = "figure|${figure.id}|dimensionText", text = figure.dimensionText, modifier = Modifier.padding(10.dp), color = Color(0xFF152F43), style = MaterialTheme.typography.labelMedium)
        }
    }
}
