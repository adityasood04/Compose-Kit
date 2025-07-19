package com.example.composekit.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type

@Composable
fun OtpInputField(
    otpLength: Int = 6,
    otpValue: String,
    onOtpChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .clickable { focusRequester.requestFocus() }
    ) {
        BasicTextField(
            value = otpValue,
            onValueChange = {
                if (it.length <= otpLength && it.all { c -> c.isDigit() }) {
                    onOtpChange(it)
                }
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onKeyEvent {
                    if (it.type == KeyEventType.KeyDown && it.key == Key.Backspace && otpValue.isNotEmpty()) {
                        onOtpChange(otpValue.dropLast(1))
                        true
                    } else false
                }
                .focusable(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(otpLength) { index ->
                        val char = otpValue.getOrNull(index)?.toString() ?: ""
                        val isFocused = otpValue.length == index

                        Box(
                            modifier = Modifier
                                .size(width = 50.dp, height = 60.dp)
                                .border(
                                    width = 2.dp,
                                    color = if (isFocused) MaterialTheme.colorScheme.primary else Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            },
            singleLine = true
        )
    }
}

