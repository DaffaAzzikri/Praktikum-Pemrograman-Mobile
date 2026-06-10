package com.example.modul5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.data.repository.MovieRepository
import com.example.modul5.model.Movie
import com.example.modul5.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository,
    private val apiKey: String = "5548ae707bded8a40d98609791065da4"
) : ViewModel() {

    private val _movies = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val movies: StateFlow<Resource<List<Movie>>> = _movies.asStateFlow()

    private val _detailTargetId = MutableStateFlow<Int?>(null)
    val detailTargetId: StateFlow<Int?> = _detailTargetId.asStateFlow()

    private val _wikiUrl = MutableStateFlow<String?>(null)
    val wikiUrl: StateFlow<String?> = _wikiUrl.asStateFlow()

    init {
        fetchMovies()
    }

    fun fetchMovies(forceFetch: Boolean = false) {
        viewModelScope.launch {
            repository.getPopularMovies(apiKey, forceFetch).collect { result ->
                _movies.value = result
            }
        }
    }

    fun onDetailClick(movie: Movie) {
        _detailTargetId.value = movie.id
    }

    fun onWikiClick(movie: Movie) {
        _wikiUrl.value = "https://www.themoviedb.org/movie/${movie.id}"
    }

    fun consumeDetailNavigation() {
        _detailTargetId.value = null
    }

    fun consumeWikiNavigation() {
        _wikiUrl.value = null
    }

    fun getMovieById(id: Int): Movie? {
        return (movies.value as? Resource.Success)?.data?.find { it.id == id }
    }
}
