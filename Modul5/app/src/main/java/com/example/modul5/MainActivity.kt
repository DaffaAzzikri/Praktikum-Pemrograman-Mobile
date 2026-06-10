package com.example.modul5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul5.data.local.PreferenceManager
import com.example.modul5.data.remote.RetrofitClient
import com.example.modul5.data.repository.MovieRepository
import com.example.modul5.ui.MovieDetailScreen
import com.example.modul5.ui.MovieListScreen
import com.example.modul5.ui.Routes
import com.example.modul5.ui.openUrl
import com.example.modul5.ui.theme.Modul5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul5Theme {
                val app = application as Modul5Application
                val movieRepository = MovieRepository(
                    api = RetrofitClient.tmdbApi,
                    dao = app.database.movieDao
                )

                val preferenceManager = PreferenceManager(LocalContext.current)

                val vm: MovieViewModel = viewModel(
                    factory = MovieViewModelFactory(movieRepository)
                )

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val context = LocalContext.current

                    val moviesResource by vm.movies.collectAsStateWithLifecycle()
                    val detailTargetId by vm.detailTargetId.collectAsStateWithLifecycle()
                    val wikiUrl by vm.wikiUrl.collectAsStateWithLifecycle()

                    LaunchedEffect(detailTargetId) {
                        val id = detailTargetId ?: return@LaunchedEffect
                        val movie = vm.getMovieById(id)

                        movie?.let { 
                            preferenceManager.saveLastViewedMovie(it.title)
                            Toast.makeText(context, "Terakhir dilihat: ${it.title}", Toast.LENGTH_SHORT).show()
                        }
                        
                        navController.navigate(Routes.detail(id.toString()))
                        vm.consumeDetailNavigation()
                    }

                    LaunchedEffect(wikiUrl) {
                        val url = wikiUrl ?: return@LaunchedEffect
                        context.openUrl(url)
                        vm.consumeWikiNavigation()
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Routes.List,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable(Routes.List) {
                            MovieListScreen(
                                moviesResource = moviesResource,
                                onDetailClick = vm::onDetailClick,
                                onWikiClick = vm::onWikiClick,
                                onRetry = { vm.fetchMovies(forceFetch = true) }
                            )
                        }
                        composable(Routes.DetailPattern) { backStackEntry ->
                            val idStr = backStackEntry.arguments?.getString("id")
                            val movie = idStr?.toIntOrNull()?.let { vm.getMovieById(it) }
                            MovieDetailScreen(
                                movie = movie,
                                onBack = { navController.popBackStack() },
                                onOpenWiki = { clicked ->
                                    vm.onWikiClick(clicked)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
