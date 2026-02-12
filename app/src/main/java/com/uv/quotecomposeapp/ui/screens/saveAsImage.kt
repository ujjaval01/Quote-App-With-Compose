package com.uv.quotecomposeapp.ui.screens

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import android.widget.Toast

fun saveScreenAsImage(context: Context, view: View) {

    view.post {

        val bitmap = Bitmap.createBitmap(
            view.width,
            view.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = android.graphics.Canvas(bitmap)
        view.draw(canvas)

        val filename = "Quote_${System.currentTimeMillis()}.png"

        val resolver = context.contentResolver

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/QuoteApp")
        }

        val imageUri = resolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        imageUri?.let { uri ->
            resolver.openOutputStream(uri)?.use { outputStream ->
                bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    outputStream
                )
            }

            Toast.makeText(
                context,
                "Quote Saved 📸",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
