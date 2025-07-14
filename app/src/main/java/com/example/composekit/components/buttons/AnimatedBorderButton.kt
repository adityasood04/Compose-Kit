package com.example.composekit.components.buttons

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedBorderButton(
    text: String,
    onClick: () -> Unit,
    borderColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary
    )
) {
    val transition = rememberInfiniteTransition(label = "")
    val animatedOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val animatedBrush = Brush.linearGradient(
        colors = borderColors,
        start = Offset(animatedOffset, 0f),
        end = Offset(animatedOffset + 500f, 500f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(width = 2.dp, brush = animatedBrush, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp)
    }
}