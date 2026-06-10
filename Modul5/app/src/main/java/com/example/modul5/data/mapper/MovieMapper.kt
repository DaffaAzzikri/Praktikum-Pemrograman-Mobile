package com.example.modul5.data.mapper

import com.example.modul5.data.local.MovieEntity
import com.example.modul5.data.remote.MovieDto
import com.example.modul5.model.Movie

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        voteAverage = voteAverage
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage
    )
}
