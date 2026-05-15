package com.example.modul3xml.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import timber.log.Timber

fun Context.openUrl(url: String) {
    Timber.d("Starting explicit Intent ACTION_VIEW: %s", url)
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)

    val chromeIntent = intent.setPackage("com.android.chrome")
    if (chromeIntent.resolveActivity(packageManager) != null) {
        startActivity(chromeIntent)
    } else {
        startActivity(Intent.createChooser(intent, "Open in browser"))
    }
}

