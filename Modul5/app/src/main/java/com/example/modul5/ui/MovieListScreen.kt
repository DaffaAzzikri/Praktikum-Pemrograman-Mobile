package com.example.modul5.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.modul5.model.Movie
import com.example.modul5.util.Resource

@Composable
fun MovieListScreen(
    moviesResource: Resource<List<Movie>>,
    onDetailClick: (Movie) -> Unit,
    onWikiClick: (Movie) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pageBg = MaterialTheme.colorScheme.background

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(pageBg)
    ) {
        when (moviesResource) {
            is Resource.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is Resource.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = moviesResource.message ?: "Unknown Error", color = MaterialTheme.colorScheme.error)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = onRetry) { Text("Retry") }
                }
            }
            is Resource.Success -> {
                val allMovies = moviesResource.data ?: emptyList()
                // Let's take the first 5 movies as "Featured"
                val featuredMovies = allMovies.take(5)

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    item {
                        Text(
                            text = "Featured Movies",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Spacer(Modifier.height(10.dp))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 0.dp),
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                        ) {
                            items(items = featuredMovies, key = { it.id }) { movie ->
                                MovieCard(
                                    movie = movie,
                                    isFeatured = true,
                                    onOpenWiki = { onWikiClick(movie) },
                                    onOpenDetail = { onDetailClick(movie) },
                                    modifier = Modifier.width(330.dp),
                                )
                            }
                        }
                    }

                    item {
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "All Movies",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }

                    items(items = allMovies, key = { it.id }) { movie ->
                        MovieCard(
                            movie = movie,
                            isFeatured = false,
                            onOpenWiki = { onWikiClick(movie) },
                            onOpenDetail = { onDetailClick(movie) },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}
