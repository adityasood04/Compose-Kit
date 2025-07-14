package com.example.composekit.components.buttons

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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


@Composable
fun MeltingGradientButton(
    text: String,
    onClick: () -> Unit
) {
    val alpha by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse),
        label = ""
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
