package com.example.composekit.components.buttons

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * MeltingGradientButton
 *
 * A text button with a slowly pulsing vertical gradient background.
 * The animation creates a subtle "melting" or breathing effect.
 *
 * Good for slightly highlighting non-primary actions without using a solid button.
 *
 * @param text The label displayed on the button.
 * @param onClick Called when the button is clicked.
 */
@Composable
fun MeltingGradientButton(
    text: String,
    onClick: () -> Unit
) {
    val alpha by rememberInfiniteTransition(label = "MeltingAlphaTransition").animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = "AlphaAnimation"
    )

    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Cyan.copy(alpha = alpha),
            Color.Blue.copy(alpha = alpha)
        )
    )

    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .background(brush, shape = RoundedCornerShape(6.dp))
    ) {
        Text(text = text, color = Color.White)
    }
}
