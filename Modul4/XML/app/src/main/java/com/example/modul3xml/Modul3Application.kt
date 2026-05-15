package com.example.modul3xml

import android.app.Application
import timber.log.Timber

class Modul3Application : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
