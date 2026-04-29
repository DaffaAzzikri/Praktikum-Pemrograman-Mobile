package com.example.modul3

import com.example.modul3.model.Char

object CharsData {
    private const val TekkenWikiBase = "https://tekken.fandom.com/wiki/"
    private const val FfWikiBase = "https://finalfantasy.fandom.com/wiki/"

    private fun tekken(slug: String) = "$TekkenWikiBase$slug"
    private fun ff(slug: String) = "$FfWikiBase$slug"

    val featured: Char =
        Char(
            id = "noctis",
            name = "Noctis Lucis Caelum",
            series = "Final Fantasy XV",
            featureTitle = "Main Character",
            featureDescription = "protagonis utama Final Fantasy XV.",
            wikiUrl = ff("Noctis_Lucis_Caelum"),
            imageResId = R.drawable.noctis,
        )

    val all: List<Char> =
        listOf(
            featured,
            Char(
                id = "king-tekken8",
                name = "King",
                series = "Tekken 8",
                featureTitle = "Playable Character",
                featureDescription = "Tukang Grab Ragebait.",
                wikiUrl = tekken("King"),
                imageResId = R.drawable.king,
            ),
            Char(
                id = "asuka-tekken8",
                name = "Asuka Kazama",
                series = "Tekken 8",
                featureTitle = "Playable Character",
                featureDescription = "Tornado every single time.",
                wikiUrl = tekken("Asuka_Kazama"),
                imageResId = R.drawable.asuka,
            ),
            Char(
                id = "jin-tekken8",
                name = "Jin Kazama",
                series = "Tekken 8",
                featureTitle = "Main Character",
                featureDescription = "MC plot Armor.",
                wikiUrl = tekken("Jin_Kazama"),
                imageResId = R.drawable.jin,
            ),
            Char(
                id = "kazuya-tekken8",
                name = "Kazuya Mishima",
                series = "Tekken 8",
                featureTitle = "Main Character",
                featureDescription = "Doryahhhh.",
                wikiUrl = tekken("Kazuya_Mishima"),
                imageResId = R.drawable.kazuyagoat,
            ),
            Char(
                id = "paul-tekken",
                name = "Paul Phoenix",
                series = "Tekken 8",
                featureTitle = "Tekken Character",
                featureDescription = "One hit KO.",
                wikiUrl = tekken("Paul_Phoenix"),
                imageResId = R.drawable.paul,
            ),
        )
}

