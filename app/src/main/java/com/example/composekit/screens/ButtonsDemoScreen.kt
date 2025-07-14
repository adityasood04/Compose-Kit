package com.example.composekit.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composekit.components.buttons.AnimatedBorderButton
import com.example.composekit.components.buttons.AnimatedScaleButton
import com.example.composekit.components.buttons.GlassButton
import com.example.composekit.components.buttons.GradientButton
import com.example.composekit.components.buttons.MeltingGradientButton
import com.example.composekit.components.buttons.MultiColorButton
import com.example.composekit.components.buttons.RippleLoaderButton
import com.example.composekit.components.buttons.RotatingIconButton
import com.example.composekit.components.buttons.SlideToggleButton
import com.example.composekit.ui.theme.ComposeKitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demo screen showcasing all custom and Material buttons
 */
@Composable
fun ButtonsDemoScreen() {
    var loading by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val shimmerAlpha by rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

    val cornerRadius = 6.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Button Components", style = MaterialTheme.typography.headlineSmall)

        SectionHeader("Basic Buttons")
        LabeledButton("Primary Button") {
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius)
            ) { Text("Primary") }
        }

        LabeledButton("Outlined Button") {
            OutlinedButton(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius)
            ) { Text("Outlined") }
        }

        LabeledButton("Text Button") {
            TextButton(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius)
            ) { Text("Text") }
        }

        SectionHeader("Icon Buttons")
        LabeledButton("Icon with Background") {
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.White)
            }
        }

        LabeledButton("Circle Icon Only") {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

        SectionHeader("Variant Buttons")
        LabeledButton("Filled Tonal") {
            FilledTonalButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
            ) {
                Text("Filled Tonal")
            }
        }

        LabeledButton("Elevated") {
            ElevatedButton(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
            ) {
                Text("Elevated")
            }
        }

        LabeledButton("Gradient Button") {
            GradientButton(
                text = "Sign Up",
                gradientColors = listOf(Color(0xFF00796B), Color(0xFF0097A7)),
                textColor = Color.White
            ) {}
        }

        LabeledButton("Shimmer Button") {
            Button(
                onClick = { /* TODO */ },
                shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = shimmerAlpha),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Shimmer")
            }
        }

        LabeledButton("Animated Scale") {
            AnimatedScaleButton("Tap Me")
        }

        SectionHeader("Stateful Buttons")
        LabeledButton("Loading Button") {
            Button(
                onClick = { loading = !loading },
                shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Loading...")
                } else Text("Submit")
            }
        }

        LabeledButton("Toggleable Button") {
            Button(
                onClick = { isSelected = !isSelected },
                shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFF2E7D32) else Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(if (isSelected) "Selected ✅" else "Select Me")
            }
        }

        SectionHeader("Animated Buttons")
        LabeledButton("Multi color button") {
            MultiColorButton(text = "Multi color", startColor = Color.Red, endColor = Color.Blue) {}
        }

        LabeledButton("Glassmorphic Button") {
            GlassButton("Glass ripple", onClick = { /* TODO */ })
        }

        LabeledButton("Animated Border") {
            AnimatedBorderButton(
                text = "Follow me",
                borderColors = listOf(Color.Red, Color.Green, Color.Blue),
                onClick = { /* TODO */ })
        }

        LabeledButton("Ripple button") {
            var isLoading by remember { mutableStateOf(false) }
            RippleLoaderButton(
                text = "Send",
                onClick = {
                    isLoading = true
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(5000)
                        isLoading = false
                    }
                },
                loading = isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }

        LabeledButton("Rotating Icon Button") {
            RotatingIconButton(
                text = "Sync",
                onClick = { /* TODO */ }
            )
        }


        LabeledButton("Melting Gradient Button") {
            MeltingGradientButton(
                text = "Melting",
                onClick = { /* TODO */ }
            )
        }

        LabeledButton("Slide Toggle Button") {
            var isToggled by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SlideToggleButton(
                    checked = isToggled,
                    onCheckedChange = { isToggled = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isToggled) "Toggled ON" else "Toggled OFF",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        color = MaterialTheme.colorScheme.primary,
        fontSize = 15.sp
    )
}

@Composable
fun LabeledButton(label: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp
        )
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewButtonsScreen() {
    ComposeKitTheme {
        ButtonsDemoScreen()
    }
}