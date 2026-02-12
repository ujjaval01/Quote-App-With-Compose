package com.uv.quotecomposeapp.notification

import android.app.*
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uv.quotecomposeapp.R
import com.uv.quotecomposeapp.data.Quote

class DailyQuoteWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val quote = getRandomQuote(applicationContext)

        showNotification(
            applicationContext,
            quote.text,
            quote.author
        )

        return Result.success()
    }

    private fun getRandomQuote(context: Context): Quote {

        val jsonString = context.assets.open("quotes.json")
            .bufferedReader()
            .use { it.readText() }

        val listType = object : TypeToken<List<Quote>>() {}.type
        val quotes: List<Quote> =
            Gson().fromJson(jsonString, listType)

        return quotes.random()
    }

    private fun showNotification(
        context: Context,
        quoteText: String,
        author: String
    ) {

        val channelId = "daily_quote_channel"

        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                channelId,
                "Daily Quote",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            manager.createNotificationChannel(channel)
        }

        val notification =
            NotificationCompat.Builder(context, channelId)
                .setContentTitle("Daily Quote 💡")
                .setContentText("“$quoteText” - $author")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

        manager.notify(1, notification)
    }
}
