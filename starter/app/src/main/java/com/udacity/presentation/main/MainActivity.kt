package com.udacity.presentation.main

import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.R
import com.udacity.components.buttons.ButtonState
import com.udacity.data.DefaultDownloadList
import com.udacity.data.DownloadItem
import com.udacity.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.content_main.view.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG: String = "Main Activity"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainActivityVM
    private lateinit var mainViewModelFactory: MainActivityVMFactory

    private lateinit var downloadManager: DownloadManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    private var downloadID: Long = 0
    private var downloadItem: DownloadItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindingAndVM()
        initClickListener()
        initManager()
        intiNotificationChannel(
            getString(R.string.loading_app_channel_id),
            getString(R.string.loading_app_channel_name)
        )
        initVMConnection()
        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }


//------------------------------------- Initialization ---------------------------------------------


    /**
     * Initialize the view binding and view model.
     * */
    private fun initBindingAndVM() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        mainViewModelFactory = MainActivityVMFactory(application)
        mainViewModel =
            ViewModelProvider(this, mainViewModelFactory).get(MainActivityVM::class.java)
        binding.lifecycleOwner = this

    }

    /**
     * Set onClickListener for clickable item.
     * */
    private fun initClickListener() {
        // Loading Button
        binding.mainActivityContents.loading_btn.setOnClickListener {
            downloadItem?.let {
                downloadID = mainViewModel.startDownload(
                    downloadManager,
                    it.url,
                    getString(it.nameID),
                    getString(it.descriptionID)
                ) ?: 0
            }?: Toast.makeText(this, getString(R.string.no_radio_button_selection), Toast.LENGTH_SHORT).show()
        }

        // Radio Group
        binding.mainActivityContents.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.retrofit_download_RB -> { downloadItem = DefaultDownloadList.downloadItemRetrofit }
                R.id.glide_download_RB -> { downloadItem = DefaultDownloadList.downloadItemGlide }
                R.id.load_app_download_RB -> { downloadItem = DefaultDownloadList.downloadItemLoadApp }
            }
        }
    }

    /**
     * Initialize the manager item in the activity.
     * */
    private fun initManager() {
        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    }

    /**
     * Initialize the notification channel.
     * TODO: Checking the meaning of setShowBadge
     * */
    private fun intiNotificationChannel(channelID: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
                    .apply {
                        setShowBadge(false)
                        enableLights(true)
                        lightColor = Color.BLUE
                        enableVibration(true)
                        description = getString(R.string.notification_description)
                    }
            notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    /**
     * Initialization the connection between UI components and view model.
     * */
    private fun initVMConnection() {
        mainViewModel.loadingBtnState.observe(this, Observer { stateChangeOperation(it) })
    }


//--------------------------------------------------------------------------------------------------


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }


//------------------------------------- Event Trigger And UI Update --------------------------------

    /**
     * The method handle the operation when the state is update.
     *
     * TODO: Consider the click action when the state is loading.
     * */
    private fun stateChangeOperation(state: ButtonState) {
        binding.mainActivityContents.loading_btn.changeState(state)
    }


}
