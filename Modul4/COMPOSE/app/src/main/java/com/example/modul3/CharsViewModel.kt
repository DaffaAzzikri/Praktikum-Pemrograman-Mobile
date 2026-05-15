package com.example.modul3

import androidx.lifecycle.ViewModel
import com.example.modul3.model.Char
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class CharsViewModel(
    private val appTitle: String,
) : ViewModel() {

    private val _featuredChars = MutableStateFlow<List<Char>>(emptyList())
    val featuredChars: StateFlow<List<Char>> = _featuredChars.asStateFlow()

    private val _allChars = MutableStateFlow<List<Char>>(emptyList())
    val allChars: StateFlow<List<Char>> = _allChars.asStateFlow()

    private val _detailTargetId = MutableStateFlow<String?>(null)
    val detailTargetId: StateFlow<String?> = _detailTargetId.asStateFlow()

    private val _wikiUrl = MutableStateFlow<String?>(null)
    val wikiUrl: StateFlow<String?> = _wikiUrl.asStateFlow()

    init {
        Timber.d("ViewModel diinisialisasi dengan appTitle: $appTitle")
        loadCharacters()
    }

    private fun loadCharacters() {
        val items = CharsData.all
        items.forEach { item ->
            Timber.i("Data item masuk ke dalam list: id=${item.id}, name=${item.name}")
        }
        _featuredChars.value = listOf(CharsData.featured)
        _allChars.value = items
    }

    fun onDetailClick(char: Char) {
        Timber.i("Tombol Detail ditekan: ${char.name} (${char.id})")
        Timber.i(
            "Navigating to detail, selected: " +
                "id=${char.id}, name=${char.name}, series=${char.series}, " +
                "feature=${char.featureTitle}: ${char.featureDescription}",
        )
        _detailTargetId.value = char.id
    }

    fun onExplicitIntentClick(char: Char) {
        Timber.i("Tombol Explicit Intent ditekan: ${char.name} → ${char.wikiUrl}")
        _wikiUrl.value = char.wikiUrl
    }

    fun consumeDetailNavigation() {
        _detailTargetId.value = null
    }

    fun consumeWikiNavigation() {
        _wikiUrl.value = null
    }

    fun getCharById(id: String): Char? = _allChars.value.firstOrNull { it.id == id }
}
