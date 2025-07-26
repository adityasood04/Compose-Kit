package com.example.composekit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composekit.screens.MoodDiaryScreen
import com.example.composekit.screens.AnimationScreen
import com.example.composekit.screens.ButtonsDemoScreen
import com.example.composekit.screens.HomeScreen
import com.example.composekit.screens.InstagramDemoScreen
import com.example.composekit.screens.TextFieldsDemoScreen
import com.example.composekit.screens.TinderSwipeScreen
import com.example.composekit.ui.theme.ComposeKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKitTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavigationComponent(navController)
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Buttons.route) { ButtonsDemoScreen() }
        composable(Screen.TextField.route) { TextFieldsDemoScreen() }
        composable(Screen.Tinder.route) { TinderSwipeScreen() }
        composable(Screen.Instagram.route) { InstagramDemoScreen() }
        composable(Screen.Dynamic_BG.route) {  MoodDiaryScreen() }
        composable(Screen.Animations.route) { AnimationScreen() }
    }
}
