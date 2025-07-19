package com.example.composekit.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

/**
 * PasswordStrengthField
 *
 * A password input field with toggleable visibility and real-time password strength indication.
 *
 * @param password The current password value.
 * @param onPasswordChange Callback when the password input is updated.
 * @param modifier Optional modifier for layout customization.
 */
@Composable
fun PasswordStrengthField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var visible by remember { mutableStateOf(false) }

    // Guys you can add your strength logic here. I have added a dummy logic.
    val strength = when {
        password.length < 6 -> "Weak" to Color.Red
        password.length < 10 -> "Medium" to Color.Yellow
        else -> "Strong" to Color(0xFF2E7D32)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { visible = !visible }) {
                    Icon(
                        imageVector = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Strength: ${strength.first}",
            color = strength.second,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
