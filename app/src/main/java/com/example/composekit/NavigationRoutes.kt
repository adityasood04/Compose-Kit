package com.example.composekit

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Buttons : Screen("buttons_screen")
    object TextField : Screen("textfield_screen")
    object Tinder : Screen("tinder_screen")
    object Instagram : Screen("instagram_screen")
    object Dynamic_BG : Screen("dynamic_bg_screen")
    object Animations : Screen("animations_screen")
}