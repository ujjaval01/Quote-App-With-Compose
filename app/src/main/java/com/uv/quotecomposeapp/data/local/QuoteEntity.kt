package com.uv.quotecomposeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class QuoteEntity(

    @PrimaryKey
    val text: String,   // unique

    val author: String,
    val category: String
)
