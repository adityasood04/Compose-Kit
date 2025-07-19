package com.example.composekit.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * LeadingIconTextField
 *
 * A customizable OutlinedTextField that supports a leading icon.
 *
 * @param value Current text in the field.
 * @param onValueChange Callback when the text value changes.
 * @param label Text label shown inside the field.
 * @param leadingIcon Icon displayed at the start of the text field.
 * @param modifier Modifier to customize layout or styling.
 */
@Composable
fun LeadingIconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Username",
    leadingIcon: ImageVector = Icons.Default.Person,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true
    )
}
