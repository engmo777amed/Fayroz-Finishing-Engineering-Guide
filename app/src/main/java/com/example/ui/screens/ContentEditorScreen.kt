package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.EditableField
import com.example.data.EditableRecord
import com.example.ui.FinishingViewModel
import com.example.ui.components.EditFieldDialog
import com.example.ui.components.FayrozTopBar

@Composable
fun ContentEditorScreen(viewModel: FinishingViewModel, onBack: () -> Unit) {
    val store = viewModel.repository.textStore
    val revision = store.revision.collectAsState().value
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<EditableRecord?>(null) }
    var field by remember { mutableStateOf<EditableField?>(null) }
    androidx.activity.compose.BackHandler(enabled = selected != null) { selected = null; query = "" }
    Column(Modifier.fillMaxSize()) {
        FayrozTopBar(title = "تعديل المحتوى", subtitle = selected?.title,
            showBackButton = true, onBackClick = { if (selected != null) selected = null else onBack() })
        OutlinedTextField(value = query, onValueChange = { query = it }, label = { Text("بحث في العناوين والحقول") },
            modifier = Modifier.fillMaxWidth().padding(12.dp), singleLine = true)
        LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (selected == null) {
                val records = store.records.filter { record -> query.isBlank() || record.title.contains(query, true) || record.kind.contains(query) || record.fields.any { store.value(it.key, it.original).contains(query, true) } }
                items(records, key = { it.scope }) { record ->
                    Card(Modifier.fillMaxWidth().clickable { selected = record; query = "" }) {
                        Column(Modifier.padding(12.dp)) {
                            Text(record.fields.firstOrNull { it.key.endsWith("|titleAr") || it.key.endsWith("|title") || it.key.endsWith("|marketNameAr") || it.key.endsWith("|itemTitle") }?.let { store.value(it.key, it.original) } ?: record.title, style = MaterialTheme.typography.titleSmall)
                            Text(record.kind, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            } else {
                val fields = selected!!.fields.filter { query.isBlank() || it.label.contains(query) || store.value(it.key, it.original).contains(query, true) }
                items(fields, key = { it.key }) { item ->
                    Card(Modifier.fillMaxWidth().clickable { field = item }) {
                        Column(Modifier.padding(12.dp)) {
                            Text(item.label + if (store.isEdited(item.key)) " • معدل" else "", style = MaterialTheme.typography.titleSmall)
                            Text(store.value(item.key, item.original), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
    if (field != null) EditFieldDialog(store, field!!) { field = null }
}
