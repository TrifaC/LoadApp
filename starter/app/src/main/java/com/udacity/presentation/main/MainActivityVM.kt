package com.udacity.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainActivityVM(application: Application): AndroidViewModel(application) {
    companion object {
        private const val LOG_TAG: String = "MainActivityVM"
    }
}