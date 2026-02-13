package com.uv.quotecomposeapp.ui.screens

import android.content.*
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uv.quotecomposeapp.loader.DotsLoader
import com.uv.quotecomposeapp.viewmodel.QuoteViewModel

@Composable
fun QuotesScreen(
    category: String?,
    viewModel: QuoteViewModel,
    navController: NavController
) {

    val favorites by viewModel.favorites.observeAsState(emptyList())
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "All Quotes ❤️",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 6.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (viewModel.allQuotes.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                DotsLoader()
            }

        } else {

            val quotes = remember(viewModel.allQuotes, category) {
                if (category.isNullOrEmpty())
                    viewModel.allQuotes.shuffled()
                else
                    viewModel.allQuotes
                        .filter { it.category == category }
                        .shuffled()
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(quotes) { quote ->

                    val isLiked = favorites.any { it.text == quote.text }

                    val scale by animateFloatAsState(
                        targetValue = if (isLiked) 1.3f else 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy
                        )
                    )

                    Card(
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                val encodedText =
                                    Uri.encode(quote.text)

                                val encodedAuthor =
                                    Uri.encode(quote.author)

                                navController.navigate(
                                    "detail/$encodedText/$encodedAuthor"
                                )
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "“${quote.text}”",
                                fontSize = 18.sp,
                                lineHeight = 26.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "— ${quote.author}",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                Row {

                                    IconButton(
                                        onClick = {
                                            val fullText =
                                                "${quote.text}\n\n— ${quote.author}"
                                            copyToClipboard(
                                                context,
                                                fullText
                                            )
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.ContentCopy,
                                            contentDescription = "Copy"
                                        )
                                    }

                                    IconButton(
                                        onClick = {
                                            val fullText =
                                                "${quote.text}\n\n— ${quote.author}"
                                            shareQuote(
                                                context,
                                                fullText
                                            )
                                        }
                                    ) {
                                        Icon(
                                            Icons.Default.Share,
                                            contentDescription = "Share"
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = {
                                        viewModel.toggleFavorite(quote)
                                    }
                                ) {
                                    Icon(
                                        imageVector =
                                            if (isLiked)
                                                Icons.Default.Favorite
                                            else
                                                Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                        tint =
                                            if (isLiked)
                                                MaterialTheme.colorScheme.primary
                                            else
                                                MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.scale(scale)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun copyToClipboard(context: Context, text: String) {
    val clipboard =
        context.getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager

    val clip = ClipData.newPlainText("quote", text)
    clipboard.setPrimaryClip(clip)

    Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
}

fun shareQuote(context: Context, text: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, text)

    context.startActivity(
        Intent.createChooser(intent, "Share via")
    )
}
