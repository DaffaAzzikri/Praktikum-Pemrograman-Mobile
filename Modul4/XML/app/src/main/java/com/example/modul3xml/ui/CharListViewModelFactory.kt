package com.example.modul3xml.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharListViewModelFactory(
    private val screenTag: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharListViewModel::class.java)) {
            return CharListViewModel(screenTag) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
