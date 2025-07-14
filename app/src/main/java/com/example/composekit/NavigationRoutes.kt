package com.example.composekit

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Buttons : Screen("buttons_screen")
    object Animations : Screen("animations_screen")
}