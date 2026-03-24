package com.example.moosic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MusicListScreen()
            }
        }
    }
}

@Composable
fun MusicListScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "Moosic - Music App",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Popular Music",
                fontWeight = FontWeight.Bold
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(MusicData.musicList) { music ->
                    MusicRowItem(music)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "All Music",
                fontWeight = FontWeight.Bold
            )
        }

        items(MusicData.musicList) { music ->
            MusicItem(music)
        }
    }
}

@Composable
fun MusicRowItem(music: Music) {

    Card(
        modifier = Modifier.width(150.dp)
    ) {

        Column {

            Image(
                painter = painterResource(id = music.image),
                contentDescription = music.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = music.title,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = music.artist,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun MusicItem(music: Music) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column {

            Box {

                Image(
                    painter = painterResource(id = music.image),
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
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            }

            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = music.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(text = "Artist: ${music.artist}")
                Text(text = "Mood: ${music.mood}")

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Play Music")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMusic() {
    MaterialTheme {
        MusicListScreen()
    }
}