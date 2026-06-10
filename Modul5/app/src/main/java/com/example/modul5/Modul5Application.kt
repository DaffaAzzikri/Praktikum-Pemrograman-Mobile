package com.example.modul5

import android.app.Application
import androidx.room.Room
import com.example.modul5.data.local.MovieDatabase
import timber.log.Timber
import kotlin.jvm.java

class Modul5Application : Application() {

    val database: MovieDatabase by lazy {
        Room.databaseBuilder(
            this,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
