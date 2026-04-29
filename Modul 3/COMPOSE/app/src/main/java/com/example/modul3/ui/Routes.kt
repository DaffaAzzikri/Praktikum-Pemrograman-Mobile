package com.example.modul3.ui

import android.net.Uri

object Routes {
    const val List = "list"
    const val DetailBase = "detail"
    const val DetailPattern = "detail/{id}"

    fun detail(id: String): String = "$DetailBase/${Uri.encode(id)}"
}

