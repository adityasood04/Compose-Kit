package com.example.composekit.components.buttons

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * RotatingIconButton
 *
 * A button with a rotating icon animation, useful for actions like refresh or sync.
 * The icon spins continuously to indicate a repeating or loading-like state.
 *
 * @param text The text displayed next to the icon.
 * @param icon The icon to rotate (defaults to [Icons.Default.Refresh]).
 * @param onClick Called when the button is clicked.
 */
@Composable
fun RotatingIconButton(
    text: String = "Sync",
    icon: ImageVector = Icons.Default.Refresh,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "RotationTransition")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart
        ),
        label = "RotationAngle"
    )

    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .graphicsLayer { rotationZ = angle }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}
