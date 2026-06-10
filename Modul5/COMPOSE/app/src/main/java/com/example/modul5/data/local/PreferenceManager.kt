package com.example.modul5.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)

    fun saveLastViewedMovie(title: String) {
        sharedPreferences.edit().putString(KEY_LAST_VIEWED, title).apply()
    }

    fun getLastViewedMovie(): String? {
        return sharedPreferences.getString(KEY_LAST_VIEWED, null)
    }

    companion object {
        private const val KEY_LAST_VIEWED = "last_viewed_movie"
    }
}
