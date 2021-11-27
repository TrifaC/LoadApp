package com.udacity.presentation.main

import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.R
import com.udacity.data.DownloadItem
import com.udacity.databinding.ActivityMainBinding
import com.udacity.util.Constants
import kotlinx.android.synthetic.main.content_main.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainActivityVM
    private lateinit var mainViewModelFactory: MainActivityVMFactory

    private lateinit var downloadManager: DownloadManager

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    private var downloadID: Long = 0
    private var downloadItem: DownloadItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindingAndVM()
        initManager()
        initClickListener()
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
     *
     * TODO: Change the constant value to data class value, also change the structure.
     * */
    private fun initClickListener() {
        // Loading Button
        binding.mainActivityContents.loading_btn.setOnClickListener {
            downloadItem?.let {
                downloadID = mainViewModel.changeDownloadState(
                    downloadManager,
                    it.url,
                    it.name,
                    it.description
                ) ?: 0
            }?: Toast.makeText(this, "Please choose a item", Toast.LENGTH_SHORT).show()
        }
        binding.mainActivityContents.radioGroup.setOnClickListener {
            if (it is RadioButton) {
                val checked = it.isChecked
                when (it.id) {
                    R.id.retrofit_download_RB -> {
                        if (checked) {
                            downloadItem = DownloadItem(
                                Constants.DOWNLOAD_URL_RETROFIT,
                                getString(R.string.file_name_retrofit),
                                getString(R.string.file_description_retrofit)
                            )
                        }
                    }
                    R.id.glide_download_RB -> { if (checked) {
                        downloadItem = DownloadItem(
                            Constants.DOWNLOAD_URL_GLIDE,
                            getString(R.string.file_name_glide),
                            getString(R.string.file_description_glide)
                        )
                    }}
                    R.id.load_app_download_RB -> {if (checked) {
                        downloadItem = DownloadItem(
                            Constants.DOWNLOAD_URL_PROJECT_STARTER,
                            getString(R.string.file_name_loadapp),
                            getString(R.string.file_description_loadapp)
                        )
                    }}
                }
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
     * Initialization the connection between UI components and view model.
     * */
    private fun initVMConnection() {
        mainViewModel.loadingBtnState.observe( this, Observer { binding.mainActivityContents.loading_btn.changeState(it) } )
    }


//--------------------------------------------------------------------------------------------------


    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }


}
