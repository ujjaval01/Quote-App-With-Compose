package com.uv.quotecomposeapp.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.uv.quotecomposeapp.ui.screens.FavoritesScreen
import com.uv.quotecomposeapp.ui.screens.HomeScreen
import com.uv.quotecomposeapp.ui.screens.QuotesScreen
import com.uv.quotecomposeapp.ui.screens.SettingsScreen
import com.uv.quotecomposeapp.viewmodel.QuoteViewModel

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val viewModel: QuoteViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {

            composable("home") {
                HomeScreen(navController, viewModel)
            }

            composable("quotes?category={category}") { backStackEntry ->
                val category =
                    backStackEntry.arguments?.getString("category")
                QuotesScreen(category, viewModel)
            }

            composable("favorites") {
                FavoritesScreen(viewModel)
            }

            composable("settings") {
                SettingsScreen()
            }
        }
    }
}
