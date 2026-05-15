package com.example.modul3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.modul3.model.Char

@Composable
fun CharsListScreen(
    featured: List<Char>,
    all: List<Char>,
    onDetailClick: (Char) -> Unit,
    onExplicitIntentClick: (Char) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pageBg = MaterialTheme.colorScheme.background

    LazyColumn(
        modifier = modifier.fillMaxSize().background(pageBg),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            Text(
                text = "Featured Characters",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                items(items = featured, key = { it.id }) { char ->
                    CharCard(
                        char = char,
                        isFeatured = true,
                        onOpenWiki = { onExplicitIntentClick(char) },
                        onOpenDetail = { onDetailClick(char) },
                        modifier = Modifier.width(330.dp),
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(10.dp))
            Text(
                text = "All Characters",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        items(items = all, key = { it.id }) { char ->
            CharCard(
                char = char,
                isFeatured = false,
                onOpenWiki = { onExplicitIntentClick(char) },
                onOpenDetail = { onDetailClick(char) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
