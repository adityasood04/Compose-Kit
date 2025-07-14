package com.example.composekit.components.buttons

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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
        val infiniteTransition = rememberInfiniteTransition(label = "")
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200),
                repeatMode = RepeatMode.Restart
            ), label = ""
        ).value
    } else 0f

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (loading) {
            Canvas(modifier = Modifier.size(160.dp)) {
                drawCircle(color = rippleColor, radius = rippleRadius)
            }
        }

        Button(
            onClick = { if (!loading) onClick() },
            shape = shape,
            enabled = !loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = containerColor.copy(alpha = 0.6f),
                disabledContentColor = contentColor.copy(alpha = 0.6f)
            )
        ) {
            Text(
                text = if (loading) "Processing..." else text,
                fontWeight = fontWeight,
                fontSize = fontSize
            )
        }
    }
}