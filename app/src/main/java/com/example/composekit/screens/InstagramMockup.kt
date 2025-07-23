package com.example.composekit.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composekit.R
import kotlinx.coroutines.delay


/**
 * Instagram Home page mockup.
 * Includes stories horizontal list and posts vertical list.
 *
 * NOTE:
 * I have added dummy data and logics for likes. In production level apps it should be from api calls.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramDemoScreen() {

    //dummy data
    val dummyUsers = remember {
        mutableStateListOf(
            User("Aditya", 22, "Himachal Pradesh", R.drawable.adi_dp),
            User("Elon", 54, "Earth", R.drawable.elon_dp),
            User("Shinchan", 5, "Kasukabe, Japan", R.drawable.shin_dp),
            User("Doraemon", 12, "Tokyo, Japan", R.drawable.dora_dp),
            User("Bheem", 10, "Dholakpur", R.drawable.bheem_dp),
            User("Jerry", 3, "Mouse Hole, NYC", R.drawable.jerry_dp),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Instagram Mockup", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        ) {
            item { StorySection(users = dummyUsers) }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(MaterialTheme.colorScheme.onSurface)
                )
            }
            items(dummyUsers) { user ->
                PostCard(user)
            }
        }
    }
}


// Story component
@Composable
fun StorySection(users: List<User>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        items(users) { user ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Image(
                    painter = painterResource(id = user.dp),
                    contentDescription = user.name,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.error, CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(user.name, fontSize = 12.sp)
            }
        }
    }
}


// User and post component
@Composable
fun PostCard(user: User) {
    var isLiked by remember { mutableStateOf(false) }
    var showHeart by remember { mutableStateOf(false) }

    val heartAlpha by animateFloatAsState(
        targetValue = if (showHeart) 1f else 0f,
        animationSpec = tween(300),
        label = "heartAlpha"
    )
    val heartScale by animateFloatAsState(
        targetValue = if (showHeart) 1.5f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "heartScale"
    )
    LaunchedEffect(showHeart) {
        if (showHeart) {
            delay(600)
            showHeart = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = user.dp),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(user.name, fontWeight = FontWeight.SemiBold)
                Text(
                    user.address,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            isLiked = true
                            showHeart = true
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text("Post by ${user.name}", color = MaterialTheme.colorScheme.onSecondaryContainer)
            }

            if (heartAlpha > 0f) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Big Heart",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            scaleX = heartScale
                            scaleY = heartScale
                            alpha = heartAlpha
                        }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                isLiked = !isLiked
            }) {
                Icon(
                    imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    modifier = Modifier.size(24.dp),
                    tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(onClick = { }) {
                Icon(Icons.Default.ChatBubbleOutline, contentDescription = "Comment")
            }

            IconButton(onClick = { }) {
                Icon(Icons.Default.Send, contentDescription = "Share")
            }
        }



        Text(
            text = "${if (isLiked) user.age + 1 else user.age} likes",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}


