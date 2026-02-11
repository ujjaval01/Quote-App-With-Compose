package com.uv.quotecomposeapp.ui.screens

import android.content.*
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uv.quotecomposeapp.viewmodel.QuoteViewModel

@Composable
fun QuotesScreen(
    category: String?,
    viewModel: QuoteViewModel
) {

    val context = LocalContext.current
    var selectedQuote by remember { mutableStateOf<com.uv.quotecomposeapp.data.Quote?>(null) }

    val quotes = if (category.isNullOrEmpty())
        viewModel.allQuotes.shuffled()
    else
        viewModel.allQuotes.filter { it.category == category }.shuffled()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(quotes) { quote ->

            var isLiked by remember { mutableStateOf(viewModel.isFavorite(quote)) }

            val scale by animateFloatAsState(
                targetValue = if (isLiked) 1.3f else 1f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            )

            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                viewModel.toggleFavorite(quote)
                                isLiked = viewModel.isFavorite(quote)
                            },
                            onTap = {
                                selectedQuote = quote
                            }
                        )
                    }
            ) {

                // Gradient Background
                Box(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.surface,
                                    MaterialTheme.colorScheme.surfaceVariant
                                )
                            )
                        )
                        .padding(20.dp)
                ) {

                    Column {

                        Text(
                            text = "“${quote.text}”",
                            fontSize = 18.sp,
                            lineHeight = 26.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "— ${quote.author}",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            // ❤️ Animated Like
                            IconButton(
                                onClick = {
                                    viewModel.toggleFavorite(quote)
                                    isLiked = viewModel.isFavorite(quote)
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

                            Row {

                                // 📤 Share
                                IconButton(
                                    onClick = {
                                        shareQuote(
                                            context,
                                            "${quote.text} - ${quote.author}"
                                        )
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null
                                    )
                                }

                                // 📋 Copy
                                IconButton(
                                    onClick = {
                                        copyToClipboard(
                                            context,
                                            "${quote.text} - ${quote.author}"
                                        )
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ContentCopy,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // 📖 Full Screen Dialog
    selectedQuote?.let { quote ->
        AlertDialog(
            onDismissRequest = { selectedQuote = null },
            confirmButton = {
                TextButton(onClick = { selectedQuote = null }) {
                    Text("Close")
                }
            },
            text = {
                Column {
                    Text("“${quote.text}”", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("— ${quote.author}")
                }
            }
        )
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

private fun shareQuote(context: Context, text: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, text)

    context.startActivity(
        Intent.createChooser(intent, "Share via")
    )
}

