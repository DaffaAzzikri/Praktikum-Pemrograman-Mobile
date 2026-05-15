package com.example.modul3xml.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul3xml.model.Char
import com.example.modul3xml.model.CharsData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class CharListViewModel(
    private val screenTag: String
) : ViewModel() {

    private val _featuredChars = MutableStateFlow<List<Char>>(emptyList())
    val featuredChars: StateFlow<List<Char>> = _featuredChars.asStateFlow()

    private val _allChars = MutableStateFlow<List<Char>>(emptyList())
    val allChars: StateFlow<List<Char>> = _allChars.asStateFlow()

    private val _detailClick = MutableSharedFlow<Char>(extraBufferCapacity = 1)
    val detailClick: SharedFlow<Char> = _detailClick.asSharedFlow()

    private val _wikiClick = MutableSharedFlow<Char>(extraBufferCapacity = 1)
    val wikiClick: SharedFlow<Char> = _wikiClick.asSharedFlow()

    init {
        loadLists()
    }

    private fun loadLists() {
        val featured = listOf(CharsData.featured)
        val all = CharsData.all

        featured.forEach { char ->
            Timber.d("[%s] Item added to featured list: %s (id=%s)", screenTag, char.name, char.id)
        }
        all.forEach { char ->
            Timber.d("[%s] Item added to all list: %s (id=%s)", screenTag, char.name, char.id)
        }

        _featuredChars.value = featured
        _allChars.value = all
    }

    fun onDetailClicked(char: Char) {
        Timber.d("Detail button pressed: %s (id=%s)", char.name, char.id)
        Timber.d(
            "Navigating to detail — selected: name=%s, series=%s, wikiUrl=%s",
            char.name,
            char.series,
            char.wikiUrl
        )
        viewModelScope.launch { _detailClick.emit(char) }
    }

    fun onWikiClicked(char: Char) {
        Timber.d("Explicit Intent button pressed: %s -> %s", char.name, char.wikiUrl)
        viewModelScope.launch { _wikiClick.emit(char) }
    }
}
