package com.udacity.presentation.main

import android.app.Application
import android.app.DownloadManager
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.components.buttons.ButtonState
import com.udacity.util.DownloadUtil

class MainActivityVM(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val LOG_TAG: String = "MainActivityVM"
    }

    private lateinit var loadingTimer: CountDownTimer

    /** The value to store the state of the loading button. */
    private val _loadingBtnState = MutableLiveData<ButtonState>()
    val loadingBtnState: LiveData<ButtonState>
        get() = _loadingBtnState


//------------------------------------- Initialization ---------------------------------------------


    init {
        _loadingBtnState.value = ButtonState.TO_CLICK
        initLoadingTimer()
    }

    /**
     * The timer is used to simulate the period of download.
     * */
    private fun initLoadingTimer() {
        loadingTimer = object: CountDownTimer(3000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d(LOG_TAG, "onTick: run().")
            }
            override fun onFinish() {
                finishDownload()
            }
        }
    }


//------------------------------------- Event Trigger Function -------------------------------------

    /**
     * Test Method:
     * The method will be called when download button has been click.
     *
     * @param downloadManager used for download the file.
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

    fun finishDownload() {
        _loadingBtnState.value = ButtonState.TO_CLICK
    }


}