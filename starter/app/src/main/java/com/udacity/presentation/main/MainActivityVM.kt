package com.udacity.presentation.main

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.R
import com.udacity.components.buttons.ButtonState
import com.udacity.receivers.AlarmReceiver
import com.udacity.util.Constants
import com.udacity.util.DownloadUtil
import com.udacity.util.cancelNotification
import com.udacity.util.sendNotification

class MainActivityVM(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val LOG_TAG: String = "MainActivityVM"
    }

    private var mApplication: Application = application
    private lateinit var notificationManager: NotificationManager
    private lateinit var loadingTimer: CountDownTimer
    private lateinit var notifyPendingIntent: PendingIntent
    private var alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var notifyIntent = Intent(application, AlarmReceiver::class.java)

    /** The value to store the state of the loading button. */
    private val _loadingBtnState = MutableLiveData<ButtonState>()
    val loadingBtnState: LiveData<ButtonState>
        get() = _loadingBtnState


//------------------------------------- Initialization ---------------------------------------------


    init {
        _loadingBtnState.value = ButtonState.TO_CLICK
        initLoadingTimer()
        initPendingIntent()
        initManager()
    }

    /**
     * The timer is used to simulate the period of download.
     * */
    private fun initLoadingTimer() {
        loadingTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d(LOG_TAG, "onTick: run().")
            }

            override fun onFinish() {
                finishDownload()
            }
        }
    }

    private fun initPendingIntent() {
        notifyPendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            Constants.REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun initManager() {
        notificationManager = ContextCompat.getSystemService(
            mApplication,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelNotification()

        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            Constants.SNOOZE_BREAK,
            notifyPendingIntent
        )
    }



//------------------------------------- Event Trigger Function -------------------------------------

    /**
     * Test Method:
     * The method will be called when download button has been click.
     *
     * @param downloadManager used for download the file.
     * @param appUrl is the Url of the download item.
     * @param appName is the name of the app will be shown in the notification.
     * @param appDescription is the description of the app will be shown in the notification.
     * @return The id number of download process in this time.
     * */
    fun startDownload(
        downloadManager: DownloadManager,
        appUrl: String,
        appName: String,
        appDescription: String
    ): Long? {
        if (_loadingBtnState.value == ButtonState.TO_CLICK) {
            _loadingBtnState.value = ButtonState.LOADING
            loadingTimer.start()
            return DownloadUtil.download(
                downloadManager,
                appUrl,
                appName,
                appDescription
            )
        } else {
            return null
        }
    }

    /**
     * The action will be executed after finishing download.(Change the state and send the notification)
     * */
    fun finishDownload() {
        _loadingBtnState.value = ButtonState.TO_CLICK
        notificationManager.sendNotification(mApplication.getString(R.string.loading_app_notification_message), mApplication)
    }


}