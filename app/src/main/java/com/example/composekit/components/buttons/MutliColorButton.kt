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

/**
 * MultiColorButton
 *
 * A button with an animated background color that transitions back and forth between two colors.
 *
 * @param text The label displayed on the button.
 * @param startColor The starting color of the animation. Defaults to theme primary.
 * @param endColor The ending color of the animation. Defaults to theme secondary.
 * @param contentColor Color of the text inside the button.
 * @param onClick Called when the button is clicked.
 */
@Composable
fun MultiColorButton(
    text: String,
    startColor: Color = MaterialTheme.colorScheme.primary,
    endColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    val transition = rememberInfiniteTransition(label = "ColorTransition")
    val animatedColor by transition.animateColor(
        initialValue = startColor,
        targetValue = endColor,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "AnimatedButtonColor"
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
