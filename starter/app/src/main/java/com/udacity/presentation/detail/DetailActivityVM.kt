package com.udacity.presentation.detail

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.util.cancelNotification

class DetailActivityVM (application: Application): AndroidViewModel(application) {
    companion object {
        private const val LOG_TAG: String = "DetailActivityVM"
    }

    private val mApplication: Application = application
    private lateinit var notificationManager: NotificationManager

    private val _isNavigateToMain = MutableLiveData<Boolean>()
    val isNavigateToMain: LiveData<Boolean>
        get() = _isNavigateToMain


//------------------------------------- Initialization ---------------------------------------------


    init {
        initManager()
        initializeNavigation()
    }

    /**
     * Initialize the notification manager.(To cancel the notification from previous activity)
     *
     * @see NotificationManager
     * */
    private fun initManager() {
        notificationManager = ContextCompat.getSystemService(
            mApplication,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelNotification()
    }


//------------------------------------- Initialization ---------------------------------------------


    private fun initializeNavigation() {
        _isNavigateToMain.value = false
    }


//------------------------------------- Event Trigger Function -------------------------------------


    fun navigateToMain() {
        _isNavigateToMain.value = true
    }


}