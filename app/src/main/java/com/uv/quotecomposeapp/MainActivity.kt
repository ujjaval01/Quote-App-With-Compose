package com.uv.quotecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.uv.quotecomposeapp.screens.QuoteListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataManager.loadAssetsFromFile(applicationContext)
        setContent {
            MaterialTheme(
//                colorScheme = DarkColorScheme,
            ) {
                enableEdgeToEdge()
//                QuoteListItems(Quote("Be yourself; everyone else is already taken.", "Theophrastus"), onClick = {})
                App()
            }
        }
    }
}

@Composable
fun App() {
    if(DataManager.isDataLoaded.value){
        QuoteListScreen(data = DataManager.data){

        }
    }
}

// for making dark theme temporary
private val DarkColorScheme = darkColorScheme(
    background = Color.Black,
    surface = Color(0xFF121212),
    primary = Color.White
)

