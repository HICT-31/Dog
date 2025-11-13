package com.example.copy_dog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DogScreen(uiState: UiState) {
    when {
        uiState.isLoading -> LoadingScreen()
        else -> PhotosGridScreen(photos = uiState.imageList)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Loading Failed")
    }
}

@Composable
fun PhotosGridScreen(photos: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(photos) { photo ->
            DogPhotoCard(photo, modifier)
        }
    }
}

@Composable
fun DogPhotoCard(photo: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(photo)
            .crossfade(true)
            .build(),
        contentDescription = "Dog Photo",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(4.dp)
    )
}