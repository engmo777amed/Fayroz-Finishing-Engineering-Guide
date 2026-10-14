package com.example.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ReferenceText(text: String, fieldKey: String = "", textKey: String = "") {
    val context = LocalContext.current
    val url = Regex("https?://[^\\s]+").find(text)?.value?.trimEnd('.', ')', '،')
    if (url == null) {
        EditableText(text, fieldKey = fieldKey, textKey = textKey, style = MaterialTheme.typography.bodySmall)
    } else {
        TextButton(onClick = {
            runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
                .onFailure { Toast.makeText(context, "تعذر فتح المرجع؛ تحقق من وجود متصفح واتصال بالإنترنت", Toast.LENGTH_SHORT).show() }
        }) {
            EditableText(text, fieldKey = fieldKey, textKey = textKey, style = MaterialTheme.typography.bodySmall)
        }
    }
}
