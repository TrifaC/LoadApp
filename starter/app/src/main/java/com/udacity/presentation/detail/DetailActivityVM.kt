package com.udacity.presentation.detail

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.udacity.util.cancelNotification

class DetailActivityVM (application: Application): AndroidViewModel(application) {
    companion object {
        private const val LOG_TAG: String = "DetailActivityVM"
    }

    private val mApplication: Application = application
    private lateinit var notificationManager: NotificationManager


//------------------------------------- Initialization ---------------------------------------------


    init {
        initManager()
    }

    private fun initManager() {
        notificationManager = ContextCompat.getSystemService(
            mApplication,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelNotification()
    }

}