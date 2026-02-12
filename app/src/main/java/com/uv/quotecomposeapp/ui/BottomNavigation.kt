package com.uv.quotecomposeapp.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

@Composable
fun BottomNavigationBar(
    navController: NavController,
    favoriteCount: Int
) {

    val items = listOf(
        "home" to Icons.Default.Home,
        "quotes" to Icons.Default.FormatQuote,
        "favorites" to Icons.Default.Favorite,
        "settings" to Icons.Default.Settings
    )

    NavigationBar {

        val currentRoute =
            navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->

            NavigationBarItem(
                selected = currentRoute?.startsWith(item.first) == true,
                onClick = {
                    navController.navigate(item.first)
                },
                icon = {

                    if (item.first == "favorites" && favoriteCount > 0) {

                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(
                                        if(favoriteCount > 99) "99+"
                                        else favoriteCount.toString()
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = item.second,
                                contentDescription = null
                            )
                        }

                    } else {

                        Icon(
                            imageVector = item.second,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}
