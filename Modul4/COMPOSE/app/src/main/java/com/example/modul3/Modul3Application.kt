package com.example.modul3

import android.app.Application
import timber.log.Timber

class Modul3Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
