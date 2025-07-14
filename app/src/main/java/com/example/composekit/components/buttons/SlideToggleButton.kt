package com.example.composekit.components.buttons

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * SlideToggleButton
 *
 * A simple custom toggle switch with slide animation.
 * Changes its background and knob position when toggled.
 *
 * Useful as a lightweight alternative to standard switches.
 *
 * @param checked Whether the toggle is on or off.
 * @param onCheckedChange Called with the new value when toggled.
 */
@Composable
fun SlideToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val knobOffset by animateDpAsState(
        targetValue = if (checked) 30.dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "knobOffset"
    )

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(50))
            .background(if (checked) Color(0xFF4CAF50) else Color.LightGray)
            .clickable { onCheckedChange(!checked) },
    ) {
        Box(
            modifier = Modifier
                .offset(x = knobOffset)
                .padding(3.dp)
                .size(24.dp)
                .background(Color.White, shape = CircleShape)
        )
    }
}
