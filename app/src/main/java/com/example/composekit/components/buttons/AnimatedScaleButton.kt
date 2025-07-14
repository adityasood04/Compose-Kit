package com.example.composekit.components.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest

/**
 * AnimatedScaleButton
 *
 * A button that slightly shrinks when you press it — just to show it noticed.
 *
 * Makes the button feel more interactive and smooth.
 *
 * @param text The text shown inside the button.
 * @param onClick Called when the button is clicked.
 */
@Composable
fun AnimatedScaleButton(
    text: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100),
        label = "ButtonScaleAnimation"
    )

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collectLatest {
            when (it) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale),
        interactionSource = interactionSource,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}
