package com.example.composekit.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * GradientButton
 *
 * A simple button with a horizontal gradient background.
 *
 * Useful when you want a bit of color without overcomplicating the design.
 *
 * @param text The label inside the button.
 * @param modifier Optional modifier for layout control.
 * @param gradientColors Colors used in the horizontal gradient.
 * @param textColor Color of the text.
 * @param cornerRadius Corner radius for the button shape.
 * @param onClick Called when the button is clicked.
 */
@Composable
fun GradientButton(
    text: String,
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(Color(0xFF003045), Color(0xFF027D95)),
    textColor: Color = Color.White,
    cornerRadius: Dp = 6.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(Brush.horizontalGradient(colors = gradientColors))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
