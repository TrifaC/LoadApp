package com.udacity.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.udacity.R
import com.udacity.presentation.detail.DetailActivity
import com.udacity.receivers.SnoozeReceiver

// Create and send the notification.
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    /********************************** Build Intent **********************************************/

    // The intent to open detail page.
    val contentIntent = Intent(applicationContext, DetailActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        Constants.NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // The intent to take a snooze action.
    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        Constants.REQUEST_CODE,
        snoozeIntent,
        PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
    )

    /********************************** Notification Attribute Build ******************************/

    // Notification Image
    val notificationImage =
        BitmapFactory.decodeResource(applicationContext.resources, R.drawable.robert_keane_unsplash)

    // Notification Style
    val notificationBigPictureStyle =
        NotificationCompat.BigPictureStyle().bigPicture(notificationImage).bigLargeIcon(null)

    // Build a notification. (AutoCancel for cancelling intent after clicking)
    val notificationBuilder = NotificationCompat.Builder(
        applicationContext, applicationContext.getString(
            R.string.loading_app_channel_id
        )
    ).setSmallIcon(R.drawable.ic_assistant_black_24dp)
        .setContentTitle(applicationContext.getString(R.string.loading_app_notification_title))
        .setContentText(messageBody)
        .setAutoCancel(true)
        .setStyle(notificationBigPictureStyle)
        .setLargeIcon(notificationImage)
        .setContentIntent(contentPendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .addAction(
            Constants.NO_ICON,
            applicationContext.getString(R.string.notification_snooze_label),
            snoozePendingIntent
        )
        .addAction(
            Constants.NO_ICON,
            applicationContext.getString(R.string.notification_check_status_label),
            contentPendingIntent
        )

    // Call notify
    notify(Constants.NOTIFICATION_ID, notificationBuilder.build())
}

// Cancel the notification.
fun NotificationManager.cancelNotification() {
    cancelAll()
}