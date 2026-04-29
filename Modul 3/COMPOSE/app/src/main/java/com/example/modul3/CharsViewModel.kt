package com.example.modul3

import androidx.lifecycle.ViewModel
import com.example.modul3.model.Char

class CharsViewModel : ViewModel() {
    val featuredChars: List<Char> = CharsData.all
    val allChars: List<Char> = CharsData.all

    fun getCharById(id: String): Char? = allChars.firstOrNull { it.id == id }
}

