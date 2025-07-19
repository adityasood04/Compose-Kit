package com.example.composekit.components.textfields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ValidationTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isValid: Boolean?,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        trailingIcon = {
            AnimatedVisibility(visible = isValid != null) {
                Icon(
                    imageVector = when (isValid) {
                        true -> Icons.Default.CheckCircle
                        false -> Icons.Default.Error
                        else -> Icons.Default.Info
                    },
                    contentDescription = null,
                    tint = when (isValid) {
                        true -> Color(0xFF2E7D32)
                        false -> Color(0xFFD32F2F)
                        else -> Color.Gray
                    }
                )
            }
        },
        isError = isValid == false,
        modifier = modifier.fillMaxWidth(),
        singleLine = true
    )
}

