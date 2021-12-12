package com.udacity.receivers

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.udacity.R
import com.udacity.util.Constants
import com.udacity.util.sendNotification

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Alarm Receiver", "onReceive, run.")
        val messageTitle: String? = intent?.getStringExtra(Constants.EXTRA_MESSAGE_TITLE)
        val messageBody: String? = intent?.getStringExtra(Constants.EXTRA_MESSAGE_BODY)
        
        val notificationManager = ContextCompat.getSystemService(
            context!!,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            messageTitle!!,
            messageBody!!,
            context
        )
    }
}