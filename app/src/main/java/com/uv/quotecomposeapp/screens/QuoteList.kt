package com.uv.quotecomposeapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Add this import
import androidx.compose.runtime.Composable
import com.uv.quotecomposeapp.model.Quote

@Composable
fun QuoteList(
    data: Array<Quote>,
    onClick: (quote : Quote) -> Unit
) {
    LazyColumn {
        // This 'items' function requires the import above
        items(data) { quote ->
            QuoteListItems(
                quote = quote,
                onClick
            )
        }
    }
}