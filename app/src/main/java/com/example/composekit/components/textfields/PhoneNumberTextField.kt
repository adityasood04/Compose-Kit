package com.example.composekit.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * PhoneNumberTextField
 *
 * A reusable phone number input field with a fixed +91 country code prefix.
 * Accepts up to 10 digits and filters out any non-numeric input.
 *
 * @param number The current phone number entered by the user.
 * @param onNumberChange Callback triggered when the number changes.
 * @param modifier Modifier to customize layout and styling.
 */
@Composable
fun PhoneNumberTextField(
    number: String,
    onNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = number,
        onValueChange = {
            if (it.length <= 10 && it.all(Char::isDigit)) {
                onNumberChange(it)
            }
        },
        label = { Text("Phone Number") },
        leadingIcon = {
            Text(
                text = "+91",
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}
