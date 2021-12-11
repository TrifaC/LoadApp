package com.udacity.presentation.detail

import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.R
import com.udacity.databinding.ActivityDetailBinding
import com.udacity.util.cancelNotification
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailActivityVM
    private lateinit var detailViewModelFactory: DetailActivityVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindingAndVM()
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
    }

    private fun initClickListener() {}

}
