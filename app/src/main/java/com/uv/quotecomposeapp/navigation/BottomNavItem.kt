package com.uv.quotecomposeapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Quotes : BottomNavItem("quotes", "Quotes", Icons.Default.FormatQuote)
    object Favorites : BottomNavItem("favorites", "Favorites", Icons.Default.Favorite)
    object Settings : BottomNavItem("settings", "Settings", Icons.Default.Settings)
}
