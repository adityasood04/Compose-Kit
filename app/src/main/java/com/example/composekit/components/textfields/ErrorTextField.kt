package com.example.composekit.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * ErrorTextField
 *
 * A reusable text field that displays an error message below the input when validation fails.
 *
 * @param value Current input text.
 * @param onValueChange Callback when text is updated.
 * @param label Label for the text field. Defaults to "Email".
 * @param isError Whether the input is currently invalid.
 * @param errorMessage Message shown below the text field when isError is true.
 * @param modifier Optional modifier for customization.
 */
@Composable
fun ErrorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Email",
    isError: Boolean = false,
    errorMessage: String = "",
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
