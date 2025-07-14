package com.example.composekit.components.buttons

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * AnimatedBorderButton
 *
 * A reusable Compose button with an eye-catching animated gradient border.
 * It’s minimalist and bold enough to stand out when needed.
 *
 * @param text The text to show inside the button.
 * @param onClick Lambda triggered when the button is clicked.
 * @param borderColors List of colors used for the animated gradient border.
 * Defaults to MaterialTheme’s primary and secondary colors.
 */
@Composable
fun AnimatedBorderButton(
    text: String,
    onClick: () -> Unit,
    borderColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary
    )
) {
    val transition = rememberInfiniteTransition(label = "AnimatedBorderTransition")
    val animatedOffset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000),
            repeatMode = RepeatMode.Restart
        ),
        label = "GradientOffsetAnimation"
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
            .border(2.dp, animatedBrush, RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp
        )
    }
}
