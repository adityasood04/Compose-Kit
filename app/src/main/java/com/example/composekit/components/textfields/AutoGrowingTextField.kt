package com.example.composekit.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * AutoGrowingTextField
 *
 * A reusable multi-line text field that automatically grows up to a specified max line count.
 *
 * @param value Current text value.
 * @param onValueChange Callback when the text is updated.
 * @param label Placeholder label text. Defaults to "Enter message...".
 * @param maxLines Maximum lines before the text field stops growing. Defaults to 5.
 * @param modifier Optional modifier for layout customization.
 */
@Composable
fun AutoGrowingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Enter message...",
    maxLines: Int = 5,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = false,
        maxLines = maxLines,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}
