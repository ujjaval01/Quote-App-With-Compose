package com.uv.quotecomposeapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.uv.quotecomposeapp.viewmodel.QuoteViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: QuoteViewModel
) {

    val quotes = viewModel.allQuotes.shuffled().take(5)
    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex = (currentIndex + 1) % quotes.size
        }
    }

    val currentQuote = quotes[currentIndex]

    // Different gradients for slides
    val gradients = listOf(
        listOf(Color(0xFFff9966), Color(0xFFff5e62)),
        listOf(Color(0xFF36D1DC), Color(0xFF5B86E5)),
        listOf(Color(0xFFDA22FF), Color(0xFF9733EE)),
        listOf(Color(0xFF11998e), Color(0xFF38ef7d)),
        listOf(Color(0xFFFF512F), Color(0xFFDD2476))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6FA))
            .padding(16.dp)
    ) {

        Text(
            text = "Daily Quotes",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedContent(
            targetState = currentQuote,
            transitionSpec = {
                slideInHorizontally { it } + fadeIn() togetherWith
                        slideOutHorizontally { -it } + fadeOut()
            },
            label = ""
        ) { quote ->

            Card(
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {

                Box(
                    modifier = Modifier
                        .background(
                            Brush.linearGradient(
                                colors = gradients[currentIndex % gradients.size]
                            )
                        )
                        .padding(24.dp)
                ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Text(
                            text = "“${quote.text}”",
                            color = Color.White,
                            fontSize = 18.sp,
                            lineHeight = 26.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "— ${quote.author}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dots
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(quotes.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (index == currentIndex) 10.dp else 6.dp)
                        .background(
                            if (index == currentIndex)
                                gradients[currentIndex % gradients.size][1]
                            else
                                Color.Gray.copy(alpha = 0.3f),
                            RoundedCornerShape(50)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        val categories =
            viewModel.allQuotes.map { it.category }.distinct()

        val categoryColors = listOf(
            Color(0xFFff9a9e),
            Color(0xFFa18cd1),
            Color(0xFFfbc2eb),
            Color(0xFF84fab0),
            Color(0xFFf6d365)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(categories) { category ->

                val bgColor =
                    categoryColors.random()

                Card(
                    shape = RoundedCornerShape(22.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable {
                            navController.navigate("quotes/$category")
                        }
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(bgColor)
                            .fillMaxSize()
                    ) {

                        Text(
                            text = category,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
