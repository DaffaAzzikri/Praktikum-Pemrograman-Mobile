package com.example.modul5.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.modul5.data.remote.TmdbApi
import com.example.modul5.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    isFeatured: Boolean,
    onOpenWiki: () -> Unit,
    onOpenDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardColor = if (isFeatured) Color(0xFFDDF0FF) else Color(0xFFEAF4FF)
    val pillColor = Color(0xFF1E3A8A)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
    ) {
        Column(
            modifier = Modifier
                .background(cardColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = TmdbApi.IMAGE_BASE_URL + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        if (!isFeatured) {
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = movie.releaseDate.split("-").firstOrNull() ?: "",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = if (isFeatured) 3 else 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
            ) {
                Button(
                    onClick = onOpenWiki,
                    colors = ButtonDefaults.buttonColors(containerColor = pillColor),
                    shape = RoundedCornerShape(999.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Wiki", style = MaterialTheme.typography.labelLarge)
                }
                Button(
                    onClick = onOpenDetail,
                    colors = ButtonDefaults.buttonColors(containerColor = pillColor),
                    shape = RoundedCornerShape(999.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Detail", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}
