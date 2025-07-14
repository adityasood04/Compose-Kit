package com.example.composekit.components.buttons

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MultiColorButton(
    text: String,
    startColor: Color = MaterialTheme.colorScheme.primary,
    endColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val transition = rememberInfiniteTransition(label = "")
    val animatedColor by transition.animateColor(
        initialValue = startColor,
        targetValue = endColor,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = animatedColor),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = 14.sp
        )
    }
}
