package com.example.modul3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.modul3.model.Char

@Composable
fun CharCard(
    char: Char,
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
            modifier = Modifier.background(cardColor).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(char.imageResId),
                    contentDescription = char.name,
                    modifier = Modifier.size(68.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = char.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        if (!isFeatured) {
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = char.series,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "${char.featureTitle}: ${char.featureDescription}",
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
                ) {
                    Text("Wiki")
                }
                Button(
                    onClick = onOpenDetail,
                    colors = ButtonDefaults.buttonColors(containerColor = pillColor),
                    shape = RoundedCornerShape(999.dp),
                ) {
                    Text("Detail")
                }
            }
        }
    }
}