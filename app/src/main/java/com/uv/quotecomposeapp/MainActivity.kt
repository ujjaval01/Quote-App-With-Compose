package com.uv.quotecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        CoroutineScope(Dispatchers.IO).launch {
            delay(4500)
        }
        setContent {
            SideEffect {
                window.statusBarColor = Color.White.toArgb()
                WindowCompat.getInsetsController(window, window.decorView)
                    .isAppearanceLightStatusBars = true // dark icons
            }
            enableEdgeToEdge()
//                QuoteListItems(Quote("Be yourself; everyone else is already taken.", "Theophrastus"), onClick = {})
            MainScreen()
        }
    }
}


// for making dark theme temporary
private val DarkColorScheme = darkColorScheme(
    background = Color.Black,
    surface = Color(0xFF121212),
    primary = Color.White
)

