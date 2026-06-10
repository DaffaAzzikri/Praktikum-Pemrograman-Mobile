package com.example.modul5.data.repository

import com.example.modul5.data.local.MovieDao
import com.example.modul5.data.mapper.toMovie
import com.example.modul5.data.mapper.toMovieEntity
import com.example.modul5.data.remote.TmdbApi
import com.example.modul5.model.Movie
import com.example.modul5.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class MovieRepository(
    private val api: TmdbApi,
    private val dao: MovieDao
) {

    fun getPopularMovies(apiKey: String, forceFetch: Boolean): Flow<Resource<List<Movie>>> = flow {
        val cache = dao.getAllMovies().first().map { it.toMovie() }
        emit(Resource.Loading(cache))

        val shouldFetch = cache.isEmpty() || forceFetch

        if (shouldFetch) {
            try {
                val response = api.getPopularMovies(apiKey)
                val entities = response.results.map { it.toMovieEntity() }
                
                dao.clearMovies()
                dao.insertMovies(entities)
            } catch (e: IOException) {
                emit(Resource.Error("Koneksi internet bermasalah", cache))
            } catch (e: Exception) {
                emit(Resource.Error("Terjadi kesalahan: ${e.localizedMessage}", cache))
            }
        }

        emitAll(dao.getAllMovies().map { entities ->
            Resource.Success(entities.map { it.toMovie() })
        })
    }
}
