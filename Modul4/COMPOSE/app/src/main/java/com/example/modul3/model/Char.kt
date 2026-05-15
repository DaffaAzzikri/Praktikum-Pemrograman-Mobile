package com.example.modul3.model

import androidx.annotation.DrawableRes

data class Char(
    val id: String,
    val name: String,
    val series: String,
    val featureTitle: String,
    val featureDescription: String,
    val wikiUrl: String,
    @DrawableRes val imageResId: Int,
)

