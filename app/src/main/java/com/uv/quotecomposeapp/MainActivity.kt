package com.uv.quotecomposeapp

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.uv.quotecomposeapp.screens.DotsLoader
import com.uv.quotecomposeapp.screens.PulseCircleLoader
import com.uv.quotecomposeapp.screens.QuoteListScreen
import com.uv.quotecomposeapp.screens.QuoteLoader
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
            DataManager.loadAssetsFromFile(applicationContext)
        }
        setContent {
            SideEffect {
                window.statusBarColor = Color.White.toArgb()
                WindowCompat.getInsetsController(window, window.decorView)
                    .isAppearanceLightStatusBars = true // dark icons
            }
            enableEdgeToEdge()
//                QuoteListItems(Quote("Be yourself; everyone else is already taken.", "Theophrastus"), onClick = {})
                App()
        }
    }
}

@Composable
fun App() {
    if(DataManager.isDataLoaded.value){
        QuoteListScreen(data = DataManager.data){
        }
    }
    else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DotsLoader()
        }
    }
}



// for making dark theme temporary
private val DarkColorScheme = darkColorScheme(
    background = Color.Black,
    surface = Color(0xFF121212),
    primary = Color.White
)

