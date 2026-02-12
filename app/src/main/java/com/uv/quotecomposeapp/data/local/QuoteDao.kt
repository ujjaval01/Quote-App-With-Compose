package com.uv.quotecomposeapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<QuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(quote: QuoteEntity)

    @Delete
    suspend fun deleteFavorite(quote: QuoteEntity)
}
