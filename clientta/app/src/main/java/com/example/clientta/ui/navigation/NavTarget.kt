package com.example.clientta.ui.navigation

sealed class NavTarget(val route: String) {
    data object Home : NavTarget("home")
}

