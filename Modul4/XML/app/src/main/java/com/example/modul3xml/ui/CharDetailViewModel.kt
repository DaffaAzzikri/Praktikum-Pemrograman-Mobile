package com.example.modul3xml.ui

import androidx.lifecycle.ViewModel
import com.example.modul3xml.model.Char
import com.example.modul3xml.model.CharsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class CharDetailViewModel(
    private val charId: String
) : ViewModel() {

    private val _char = MutableStateFlow<Char?>(null)
    val char: StateFlow<Char?> = _char.asStateFlow()

    init {
        val selected = CharsData.all.firstOrNull { it.id == charId }
        if (selected != null) {
            Timber.d(
                "Detail screen — selected list item: id=%s, name=%s, series=%s, wikiUrl=%s",
                selected.id,
                selected.name,
                selected.series,
                selected.wikiUrl
            )
        } else {
            Timber.w("Detail screen — no character found for id=%s", charId)
        }
        _char.value = selected
    }
}
