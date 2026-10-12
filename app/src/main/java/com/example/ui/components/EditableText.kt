package com.example.ui.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitLongPressOrCancellation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text as PlainText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.onLongClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.data.EditableContentStore
import com.example.data.EditableField

val LocalContentStore = staticCompositionLocalOf<EditableContentStore?> { null }
val LocalRecordScope = staticCompositionLocalOf { "" }
val LocalEditorOpen = staticCompositionLocalOf<() -> Unit> { {} }

@Composable
fun EditableText(
    text: String, modifier: Modifier = Modifier, color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified, fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null, fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified, textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null, lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip, softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE, minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {}, textKey: String = "", fieldKey: String = "",
    style: TextStyle = androidx.compose.material3.LocalTextStyle.current
) {
    val store = LocalContentStore.current
    val scope = LocalRecordScope.current
    val revision = store?.revision?.collectAsState()?.value ?: 0
    val target = remember(store, scope, text, textKey, fieldKey, revision) { store?.field(fieldKey) ?: store?.target(text, scope, textKey) }
    var editing by remember(target?.key) { mutableStateOf(false) }
    val displayed = if (store != null && target != null) {
        if (target.key.startsWith("ui|")) store.value(target.key, text)
        else if (text.contains(target.original)) text.replaceFirst(target.original, store.value(target.key, target.original))
        else text
    } else text
    val editModifier = if (target == null || store == null) modifier else modifier
        .semantics { onLongClick(label = "تعديل النص") { editing = true; true } }
        .pointerInput(target.key) {
            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false)
                val held = awaitLongPressOrCancellation(down.id)
                if (held != null) {
                    editing = true
                    currentEvent.changes.forEach { it.consume() }
                }
            }
        }
    PlainText(text = displayed, modifier = editModifier, color = color, fontSize = fontSize,
        fontStyle = fontStyle, fontWeight = fontWeight, fontFamily = fontFamily,
        letterSpacing = letterSpacing, textDecoration = textDecoration, textAlign = textAlign,
        lineHeight = lineHeight, overflow = overflow, softWrap = softWrap, maxLines = maxLines,
        minLines = minLines, onTextLayout = onTextLayout, style = style)
    if (editing && target != null && store != null) {
        EditFieldDialog(store, target) { editing = false }
    }
}

@Composable
fun EditFieldDialog(store: EditableContentStore, field: EditableField, onDismiss: () -> Unit) {
    var draft by remember(field.key) { mutableStateOf(store.value(field.key, field.original)) }
    var error by remember { mutableStateOf<String?>(null) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { PlainText("تعديل ${field.label}") },
        text = {
            Column {
                OutlinedTextField(value = draft, onValueChange = { draft = it; error = null },
                    modifier = Modifier.fillMaxWidth(), minLines = 3, maxLines = 8,
                    isError = error != null, label = { PlainText("النص") })
                if (error != null) PlainText(error!!, modifier = Modifier.padding(top = 8.dp))
                TextButton(onClick = {
                    if (store.reset(field.key)) onDismiss() else error = "تعذر الحفظ؛ حاول مرة أخرى"
                }) { PlainText("استرجاع الأصل") }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                when {
                    draft.isBlank() -> error = "اكتب نصًا قبل الحفظ"
                    store.save(field.key, draft) -> onDismiss()
                    else -> error = "تعذر الحفظ؛ التعديل لم يُحفظ"
                }
            }) { PlainText("حفظ") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { PlainText("إلغاء") } }
    )
}
