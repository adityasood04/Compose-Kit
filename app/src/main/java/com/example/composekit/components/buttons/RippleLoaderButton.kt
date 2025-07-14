package com.example.composekit.components.buttons

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * RippleLoaderButton
 *
 * A button that shows a ripple animation behind it when in loading state.
 * It disables interaction while loading, and displays a subtle expanding ripple.
 *
 * Useful for form submissions, background tasks, or any async actions.
 *
 * @param text The text to display when not loading.
 * @param onClick Called when the button is clicked (only when not loading).
 * @param loading Whether the button is in a loading state.
 * @param modifier Modifier for customizing layout or size.
 * @param shape Button shape.
 * @param containerColor Background color of the button.
 * @param contentColor Color of the button text.
 * @param rippleColor Color of the animated ripple shown while loading.
 * @param fontSize Size of the text inside the button.
 * @param fontWeight Weight of the text inside the button.
 */
@Composable
fun RippleLoaderButton(
    text: String,
    onClick: () -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(50),
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    rippleColor: Color = containerColor.copy(alpha = 0.3f),
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.SemiBold
) {
    val rippleRadius = if (loading) {
        val infiniteTransition = rememberInfiniteTransition(label = "RippleAnimation")
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1200),
                repeatMode = RepeatMode.Restart
            ),
            label = "RippleRadius"
        ).value
    } else 0f

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            Canvas(modifier = Modifier.size(160.dp)) {
                drawCircle(color = rippleColor, radius = rippleRadius)
            }
        }

        Button(
            onClick = { if (!loading) onClick() },
            enabled = !loading,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = containerColor.copy(alpha = 0.6f),
                disabledContentColor = contentColor.copy(alpha = 0.6f)
            )
        ) {
            Text(
                text = if (loading) "Processing..." else text,
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }
    }
}
