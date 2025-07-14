package com.example.composekit.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GlassButton(text: String, onClick: () -> Unit) {
    val isDark = isSystemInDarkTheme()
    val backgroundBrush = Brush.linearGradient(
        colors = if (isDark)
            listOf(Color.White.copy(alpha = 0.2f), Color.White.copy(alpha = 0.05f))
        else listOf(Color.Black.copy(alpha = 0.08f), Color.Black.copy(alpha = 0.02f))
    )
    val borderColor = if (isDark) Color.White.copy(alpha = 0.3f) else Color.Black.copy(alpha = 0.1f)
    val textColor = if (isDark) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundBrush)
            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .blur(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}
