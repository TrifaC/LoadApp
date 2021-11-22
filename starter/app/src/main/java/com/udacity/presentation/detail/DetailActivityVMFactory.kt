package com.udacity.presentation.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailActivityVMFactory (private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailActivityVM::class.java)) {
            return DetailActivityVM(application) as T
        }
        throw IllegalArgumentException("Unknown VM class when creating detail activity VM.")
    }
}