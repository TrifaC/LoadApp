package com.udacity.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class DetailActivityVM (application: Application): AndroidViewModel(application) {
    companion object {
        private const val LOG_TAG: String = "DetailActivityVM"
    }
}