package com.example.composekit.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


// data class for user
data class User(val name: String, val age: Int, val address: String)

@Composable
fun TinderSwipeScreen() {
    val dummyUsers = remember {
        mutableStateListOf(
            User("Shinchan", 5, "Kasukabe, Japan"),
            User("Doraemon", 12, "Tokyo, Japan"),
            User("Bheem", 10, "Dholakpur"),
            User("Jerry", 3, "Mouse Hole, NYC"),
            User("Aditya", 22, "Himachal Pradesh"),
        )
    }
    Text(
        "Tinder Screen mockup",
        Modifier.padding(16.dp),
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 18.sp,
        textAlign = TextAlign.Center
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        dummyUsers.forEachIndexed { index, user ->
            val isTop = index == dummyUsers.lastIndex
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(400, delayMillis = index * 100)) +
                        scaleIn(
                            initialScale = 0.85f,
                            animationSpec = tween(400, delayMillis = index * 100)
                        )
            ) {
                if (isTop) {
                    SwipeableCard(user = user) {
                        dummyUsers.removeAt(index)
                    }
                } else {
                    ProfileCard(
                        user = user,
                        modifier = Modifier
                            .offset(y = (10 * (dummyUsers.size - index)).dp)
                            .graphicsLayer {
                                scaleX = 0.95f
                                scaleY = 0.95f
                            }
                    )
                }
            }
        }
    }
}


@Composable
fun SwipeableCard(
    user: User,
    onSwiped: () -> Unit
) {
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val rotationDegrees = (offsetX.value / 60).coerceIn(-40f, 40f)

    Box(
        modifier = Modifier
            .size(300.dp, 450.dp)
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .graphicsLayer(rotationZ = rotationDegrees)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        if (offsetX.value > 300f || offsetX.value < -300f) {
                            scope.launch {
                                offsetX.animateTo(
                                    targetValue = if (offsetX.value > 0) 1000f else -1000f,
                                    animationSpec = tween(300)
                                )
                                onSwiped()
                                offsetX.snapTo(0f)
                            }
                        } else {
                            scope.launch {
                                offsetX.animateTo(0f, animationSpec = tween(300))
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount.x)
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        ProfileCard(user)
    }
}


@Composable
fun ProfileCard(user: User, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(300.dp, 450.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                            ),
                            startY = 300f
                        )
                    )
            )

            // Profile image part
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                // You can load an image here
                Text(
                    text = user.name.first().toString(),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // User info part
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = user.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Age: ${user.age}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Address: ${user.address}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
        }
    }
}
