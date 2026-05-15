package com.example.modul3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharsViewModelFactory(
    private val appTitle: String,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharsViewModel::class.java)) {
            return CharsViewModel(appTitle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
