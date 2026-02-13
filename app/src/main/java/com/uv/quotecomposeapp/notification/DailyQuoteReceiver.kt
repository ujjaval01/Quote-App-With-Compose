package com.uv.quotecomposeapp.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DailyQuoteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationHelper.showDailyQuote(context)
    }
}
