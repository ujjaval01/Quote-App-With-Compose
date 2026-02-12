package com.uv.quotecomposeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = null)
        }

        Text(
            text = "About App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Daily Quotes App helps you stay motivated and inspired every day."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Version: 1.0")
        Text("Developer: UV Developer")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Made with ❤️ using Jetpack Compose",
            fontWeight = FontWeight.Medium
        )
    }
}
