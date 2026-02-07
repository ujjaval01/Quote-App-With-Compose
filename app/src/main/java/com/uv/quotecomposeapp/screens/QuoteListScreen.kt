package com.uv.quotecomposeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uv.quotecomposeapp.R
import com.uv.quotecomposeapp.model.Quote

@Composable
fun QuoteListScreen(
    data: Array<Quote>,
    onClick: (quote : Quote) -> Unit
) {
    Box {
        Column {
            Text(
                text = "\"Quotes App\"",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 38.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.Monospace,
                fontSize = 24.sp
            )

            QuoteList(
                data = data,
                onClick
            )
        }
    }
}
