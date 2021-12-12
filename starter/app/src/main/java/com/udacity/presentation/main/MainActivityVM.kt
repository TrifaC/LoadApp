package com.udacity.presentation.main

import android.app.*
import android.content.Intent
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.R
import com.udacity.components.buttons.ButtonState
import com.udacity.presentation.detail.DetailActivity
import com.udacity.util.Constants
import com.udacity.util.DownloadUtil
import com.udacity.util.cancelNotification
import com.udacity.util.sendNotification

class MainActivityVM(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val LOG_TAG: String = "MainActivityVM"
    }

    private val mApplication: Application = application
    private lateinit var notificationManager: NotificationManager
    private lateinit var loadingTimer: CountDownTimer
    private lateinit var notifyPendingIntent: PendingIntent

    private var downloadAppName: String = ""
    private var downloadAppDescription: String = ""

    private var notifyIntent = Intent(application, DetailActivity::class.java)

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

    /**
     * Initialize the pending intent to be used in notification.
     * */
    private fun initPendingIntent() {
        notifyPendingIntent = PendingIntent.getBroadcast(
            getApplication(),
            Constants.REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    /**
     * Initialize the manager to control the notification.
     * */
    private fun initManager() {
        notificationManager = ContextCompat.getSystemService(
            mApplication,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelNotification()
    }


//------------------------------------- Event Trigger Function -------------------------------------


    /**
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
            downloadAppName = appName
            downloadAppDescription = appDescription
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
        notificationManager.sendNotification(downloadAppName, downloadAppDescription , mApplication)
        Toast.makeText(mApplication, mApplication.getString(R.string.toast_finish_download), Toast.LENGTH_SHORT).show()
    }


}