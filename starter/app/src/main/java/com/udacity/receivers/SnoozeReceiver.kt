package com.udacity.receivers

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import com.udacity.util.Constants

class SnoozeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val triggerTime = SystemClock.elapsedRealtime() + Constants.SNOOZE_BREAK

        // The intent to send the notification.
        val notifyIntent = Intent(context, AlarmReceiver::class.java)
        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            Constants.REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // The intent to trigger the notification sending.
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            notifyPendingIntent
        )

        val notificationManager = ContextCompat.getSystemService(
            context!!,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelAll()
    }
}