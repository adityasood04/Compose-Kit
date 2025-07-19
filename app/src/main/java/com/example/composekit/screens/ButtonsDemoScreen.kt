package com.example.composekit.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composekit.common.LabeledComponent
import com.example.composekit.common.SectionHeader
import com.example.composekit.components.buttons.*
import com.example.composekit.ui.theme.ComposeKitTheme
import kotlinx.coroutines.*

/**
 * Demo screen showcasing all custom and Material buttons.
 */
@Composable
fun ButtonsDemoScreen() {
    var loading by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val shimmerAlpha by rememberInfiniteTransition(label = "Shimmer").animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ShimmerAlpha"
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
        LabeledComponent("Primary Button") {
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius)
            ) { Text("Primary") }
        }

        LabeledComponent("Outlined Button") {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius)
            ) { Text("Outlined") }
        }

        LabeledComponent("Text Button") {
            TextButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(cornerRadius)
            ) { Text("Text") }
        }

        SectionHeader("Icon Buttons")
        LabeledComponent("Icon with Background") {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.White)
            }
        }

        LabeledComponent("Circle Icon Only") {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(40.dp)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
                }
            }
        }

        SectionHeader("Variant Buttons")
        LabeledComponent("Filled Tonal") {
            FilledTonalButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
            ) { Text("Filled Tonal") }
        }

        LabeledComponent("Elevated") {
            ElevatedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cornerRadius))
            ) { Text("Elevated") }
        }

        LabeledComponent("Gradient Button") {
            GradientButton(
                text = "Sign Up",
                gradientColors = listOf(Color(0xFF00796B), Color(0xFF0097A7)),
                textColor = Color.White
            ) {}
        }

        LabeledComponent("Shimmer Button") {
            Button(
                onClick = {},
                shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = shimmerAlpha),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Shimmer")
            }
        }

        LabeledComponent("Animated Scale") {
            AnimatedScaleButton("Tap Me", onClick = {})
        }

        SectionHeader("Stateful Buttons")
        LabeledComponent("Loading Button") {
            Button(
                onClick = { loading = !loading },
                shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Loading...")
                } else {
                    Text("Submit")
                }
            }
        }

        LabeledComponent("Toggleable Button") {
            Button(
                onClick = { isSelected = !isSelected },
                shape = RoundedCornerShape(cornerRadius),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFF2E7D32) else Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(if (isSelected) "Selected ✅" else "Select Me")
            }
        }

        SectionHeader("Animated Buttons")
        LabeledComponent("Multi color button") {
            MultiColorButton(text = "Multi color", startColor = Color.Red, endColor = Color.Blue) {}
        }

        LabeledComponent("Glassmorphic Button") {
            GlassButton("Glass ripple") {}
        }

        LabeledComponent("Animated Border") {
            AnimatedBorderButton(
                text = "Follow me",
                borderColors = listOf(Color.Red, Color.Green, Color.Blue),
                onClick = {}
            )
        }

        LabeledComponent("Ripple button") {
            var isLoading by remember { mutableStateOf(false) }

            RippleLoaderButton(
                text = "Send",
                onClick = {
                    // guys it's just a demo for showing animation
                    isLoading = true
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(3000)
                        isLoading = false
                    }
                },
                loading = isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }

        LabeledComponent("Rotating Icon Button") {
            RotatingIconButton(text = "Sync") {}
        }

        LabeledComponent("Melting Gradient Button") {
            MeltingGradientButton(text = "Melting") {}
        }

        LabeledComponent("Slide Toggle Button") {
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
                Text(text = if (isToggled) "Toggled ON" else "Toggled OFF", fontSize = 14.sp)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewButtonsScreen() {
    ComposeKitTheme {
        ButtonsDemoScreen()
    }
}
