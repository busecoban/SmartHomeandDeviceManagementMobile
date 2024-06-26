package com.example.cse234_smarthome.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = Routes.homeScreen,
        title = "Home",
        icon = Icons.Filled.Home
    )

    object Profile : BottomBarScreen(
        route = Routes.profileScreen,
        title = "Profile",
        icon = Icons.Filled.Person
    )

    object Settings : BottomBarScreen(
        route = Routes.settingsScreen,
        title = "Settings",
        icon = Icons.Filled.Settings
    )
}
