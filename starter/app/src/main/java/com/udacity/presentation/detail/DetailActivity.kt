package com.udacity.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.R
import com.udacity.databinding.ActivityDetailBinding
import com.udacity.presentation.main.MainActivity
import com.udacity.util.Constants
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.view.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailActivityVM
    private lateinit var detailViewModelFactory: DetailActivityVMFactory

    private var appName: String = ""
    private var appDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentMessage()
        initBindingAndVM()
        initClickListener()
        initBindingVMConnection()
    }


//--------------------------- Get Message From Intent ----------------------------------------------


    private fun getIntentMessage() {
        intent?.apply {
            appName = this.getStringExtra(Constants.EXTRA_MESSAGE_TITLE)!!
            appDescription = this.getStringExtra(Constants.EXTRA_MESSAGE_BODY)!!
        }
    }


//--------------------------- Initialization -------------------------------------------------------


    /**
     * Initialize the view binding and view model.
     * */
    private fun initBindingAndVM() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setSupportActionBar(toolbar)
        detailViewModelFactory = DetailActivityVMFactory(application)
        detailViewModel = ViewModelProvider(this, detailViewModelFactory).get(DetailActivityVM::class.java)
        binding.lifecycleOwner = this

        binding.detailInfo.file_name_TV.text = appName
        binding.detailInfo.description_TV.text = appDescription

        //Test condition function
        val randomNum: Int = (0..10).random()
        if (randomNum > 5) {
            binding.detailInfo.status_TV.text = getString(R.string.detail_screen_success_label)
            binding.detailInfo.status_TV.setTextColor(resources.getColor(R.color.green))
        } else {
            binding.detailInfo.status_TV.text = getString(R.string.detail_screen_fail_label)
            binding.detailInfo.status_TV.setTextColor(resources.getColor(R.color.red))
        }
    }

    /**
     * Initialize the click listener for button.
     * */
    private fun initClickListener() {
        binding.floatingBackBtn.setOnClickListener { detailViewModel.navigateToMain() }
    }

    /**
     * Initialize the observe function.
     * */
    private fun initBindingVMConnection() {
        detailViewModel.isNavigateToMain.observe(this, {if(it)navigateToMain()})
    }

//--------------------------- Observe Function -----------------------------------------------------


    /**
     * The method will send intent to main activity.
     * */
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
