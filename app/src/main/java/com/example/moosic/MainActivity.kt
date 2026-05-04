package com.example.moosic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.moosic.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoosicTheme {
                MusicListScreen()
            }
        }
    }
}

@Composable
fun MusicListScreen() {

    var selectedMusic by remember { mutableStateOf<Music?>(null) }

    if (selectedMusic == null) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Text(
                    text = "Moosic - Music App",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Popular Music",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(MusicData.musicList) { music ->
                        MusicRowItem(music)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "All Music",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            items(MusicData.musicList) { music ->
                MusicItem(music) {
                    selectedMusic = it
                }
            }
        }

    } else {
        MusicDetailScreen(
            music = selectedMusic!!,
            onBack = { selectedMusic = null }
        )
    }
}

@Composable
fun MusicRowItem(music: Music) {

    Card(
        modifier = Modifier.width(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column {

            AsyncImage(
                model = music.image,
                contentDescription = music.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = music.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = music.artist,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun MusicItem(
    music: Music,
    onDetailClick: (Music) -> Unit
) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column {

            Box {

                AsyncImage(
                    model = music.image,
                    contentDescription = music.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {

                    Icon(
                        imageVector = if (isFavorite)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite)
                            FavoriteRed
                        else
                            MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = music.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = "Artist: ${music.artist}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = "Mood: ${music.mood}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onDetailClick(music) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Detail")
                }
            }
        }
    }
}

@Composable
fun MusicDetailScreen(
    music: Music,
    onBack: () -> Unit
) {

    var isLoading by remember { mutableStateOf(false) }
    var isAdded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Detail Music",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = music.image,
            contentDescription = music.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Title: ${music.title}")
        Text("Artist: ${music.artist}")
        Text("Mood: ${music.mood}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { isLoading = true },
            enabled = !isLoading && !isAdded,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isAdded) "Added ✓" else "Add to Playlist")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (isAdded) {
            Text("Berhasil ditambahkan ke playlist")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(3000)
            isLoading = false
            isAdded = true
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMusic() {
    MoosicTheme {
        MusicListScreen()
    }
}