package com.example.modul3.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.modul3.model.Char

@Composable
fun CharsDetailScreen(
    char: Char?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val pillColor = Color(0xFF1E3A8A)

    if (char == null) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Data tidak ditemukan.", style = MaterialTheme.typography.titleLarge)
            Button(onClick = onBack, shape = RoundedCornerShape(999.dp)) { Text("Kembali")}
        }
        return
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Detail", style = MaterialTheme.typography.titleLarge)
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = pillColor),
                shape = RoundedCornerShape(999.dp),
            ) {
                Text("Kembali")
            }
        }

        Image(
            painter = painterResource(char.imageResId),
            contentDescription = char.name,
            modifier = Modifier.fillMaxWidth().height(220.dp).clip(RoundedCornerShape(22.dp)),
            contentScale = ContentScale.Crop,
        )

        Text(text = char.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = char.series, style = MaterialTheme.typography.titleMedium)
        Text(
            text = "${char.featureTitle}: ${char.featureDescription}",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { context.openUrl(char.wikiUrl) },
            colors = ButtonDefaults.buttonColors(containerColor = pillColor),
            shape = RoundedCornerShape(999.dp),
        ) {
            Text("Buka Wiki")
        }
    }
}

