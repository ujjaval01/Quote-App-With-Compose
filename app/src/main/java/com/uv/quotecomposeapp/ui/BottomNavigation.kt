package com.uv.quotecomposeapp.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        "home" to Icons.Default.Home,
        "quotes?category=" to Icons.Default.FormatQuote,
        "favorites" to Icons.Default.Favorite,
        "settings" to Icons.Default.Settings
    )

    NavigationBar {

        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute?.startsWith(item.first.substringBefore("?")) == true,
                onClick = {
                    navController.navigate(item.first)
                },
                icon = {
                    Icon(item.second, contentDescription = null)
                }
            )
        }
    }
}
