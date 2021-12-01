package com.udacity.util

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.udacity.R

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val notificationBuilder = NotificationCompat.Builder(
        applicationContext, applicationContext.getString(
            R.string.loading_app_channel_id
        )
    ).setSmallIcon(R.drawable.ic_assistant_black_24dp)
        .setContentTitle(applicationContext.getString(R.string.loading_app_notification_title))
        .setContentText(messageBody)

    notify(Constants.NOTIFICATION_ID, notificationBuilder.build())
}