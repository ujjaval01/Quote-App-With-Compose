package com.uv.quotecomposeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalView
import android.view.View
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun QuoteDetailScreen(
    text: String,
    author: String,
    navController: NavController
) {

    val context = LocalContext.current
    val view = LocalView.current
    val quoteView = remember { mutableStateOf<View?>(null) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE6DCCF),
                        Color(0xFFB7E4C7),
                        Color(0xFF95D5B2)
                    )
                )
            )
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }

                // THERE DOT(:) MENU BUTTON
//                IconButton(onClick = { /* future menu */ }) {
//                    Icon(Icons.Default.MoreVert, contentDescription = null)
//                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            AndroidView(
                factory = { context ->
                    android.widget.FrameLayout(context).apply {
                        quoteView.value = this
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) { frameLayout ->

                frameLayout.removeAllViews()

                val composeView = androidx.compose.ui.platform.ComposeView(context).apply {
                    setContent {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFE6DCCF),
                                            Color(0xFFB7E4C7),
                                            Color(0xFF95D5B2)
                                        )
                                    )
                                )
                                .padding(40.dp)
                        ) {

                            Column {

                                Text(
                                    text = "❝",
                                    fontSize = 60.sp,
                                    color = Color(0xFF000000).copy(alpha = 0.5f)
                                )

                                Text(
                                    text = text,
                                    fontSize = 26.sp,
                                    lineHeight = 36.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = "❞",
                                    fontSize = 60.sp,
                                    color = Color(0xFF000000).copy(alpha = 0.5f),
                                            modifier = Modifier.align(
                                        androidx.compose.ui.Alignment.End
                                    )
                                )


                                Text(
                                    text = author.uppercase(),
                                    fontSize = 12.sp,
                                    letterSpacing = 2.sp,
                                    color = Color.DarkGray
                                )


                            }
                        }
                    }
                }

                frameLayout.addView(composeView)
            }



            Spacer(modifier = Modifier.weight(1f))

            // Bottom Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = {
                        val fullText = "$text\n\n— $author"
                        shareQuote(context, fullText)
                    }
                ) {
                    Icon(Icons.Default.Share, contentDescription = null)
                }


                //  save as image
                IconButton(
                    onClick = {
                        quoteView.value?.let {
                            saveScreenAsImage(context, it)
                        }
                    }
                ) {
                    Icon(Icons.Default.Save, contentDescription = null)
                }
            }
        }
    }
}

