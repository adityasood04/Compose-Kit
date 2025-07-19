package com.example.composekit.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

/**
 * CardNumberField
 *
 * A card number input field that formats text in groups of 4 and manages cursor correctly.
 *
 * @param value Raw unformatted card number (digits only).
 * @param onValueChange Callback with updated raw card number (digits only).
 * @param modifier Optional layout modifier.
 */
@Composable
fun CardNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var fieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = formatCardNumber(value),
                selection = TextRange(formatCardNumber(value).length)
            )
        )
    }

    OutlinedTextField(
        value = fieldValue,
        onValueChange = {
            val digits = it.text.filter { ch -> ch.isDigit() }.take(16)
            val formatted = formatCardNumber(digits)

            val newCursorPos = calculateCursorPosition(it.text, formatted, it.selection.end)

            fieldValue = it.copy(
                text = formatted,
                selection = TextRange(newCursorPos)
            )
            onValueChange(digits)
        },
        label = { Text("Card Number") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.fillMaxWidth()
    )
}

private fun formatCardNumber(input: String): String {
    return input.chunked(4).joinToString(" ")
}

private fun calculateCursorPosition(old: String, new: String, oldCursor: Int): Int {
    val unformattedOld = old.filter { it.isDigit() }
    val unformattedNew = new.filter { it.isDigit() }

    var unformattedCursor = unformattedOld.take(oldCursor).length
    var spaceCount = 0

    for (i in new.indices) {
        if (new[i].isDigit()) {
            if (--unformattedCursor < 0) break
        } else {
            spaceCount++
        }
    }
    return oldCursor + spaceCount
}
