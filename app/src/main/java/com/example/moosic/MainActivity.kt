package com.example.moosic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Moosic - Mood Music App",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        MusicData.musicList.forEach { music ->

            MusicItem(music)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MusicItem(music: Music) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = music.image),
            contentDescription = music.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = music.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Artist: ${music.artist}"
        )

        Text(
            text = "Mood: ${music.mood}"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { }
        ) {
            Text("Play Music")
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