package com.example.modul3xml.model

import com.example.modul3xml.R

object CharsData {
    private const val TekkenWikiBase = "https://tekken.fandom.com/wiki/"
    private const val FfWikiBase = "https://finalfantasy.fandom.com/wiki/"

    private fun tekken(slug: String) = "$TekkenWikiBase$slug"
    private fun ff(slug: String) = "$FfWikiBase$slug"

    val featured: Char = Char(
        id = "noctis",
        imageResId = R.drawable.noctis,
        name = "Noctis Lucis Caelum",
        series = "Final Fantasy XV",
        featureTitle = "Main Character:",
        featureDescription = "protagonis utama Final Fantasy XV.",
        wikiUrl = ff("Noctis_Lucis_Caelum")
    )

    val all: List<Char> = listOf(
        featured,
        Char(
            id = "king-tekken8",
            imageResId = R.drawable.king,
            name = "King",
            series = "Tekken 8",
            featureTitle = "Playable Character:",
            featureDescription = "Tukang Grab Ragebait.",
            wikiUrl = tekken("King")
        ),
        Char(
            id = "asuka-tekken8",
            imageResId = R.drawable.asuka,
            name = "Asuka Kazama",
            series = "Tekken 8",
            featureTitle = "Playable Character:",
            featureDescription = "Tornado every single time.",
            wikiUrl = tekken("Asuka_Kazama")
        ),
        Char(
            id = "jin-tekken8",
            imageResId = R.drawable.jin,
            name = "Jin Kazama",
            series = "Tekken 8",
            featureTitle = "Main Character:",
            featureDescription = "MC plot Armor.",
            wikiUrl = tekken("Jin_Kazama")
        ),
        Char(
            id = "kazuya-tekken8",
            imageResId = R.drawable.kazuyagoat,
            name = "Kazuya Mishima",
            series = "Tekken 8",
            featureTitle = "Main Character:",
            featureDescription = "Doryahhhh.",
            wikiUrl = tekken("Kazuya_Mishima")
        ),
        Char(
            id = "paul-tekken",
            imageResId = R.drawable.paul,
            name = "Paul Phoenix",
            series = "Tekken 8",
            featureTitle = "Tekken Character:",
            featureDescription = "One hit KO.",
            wikiUrl = tekken("Paul_Phoenix")
        )
    )
}