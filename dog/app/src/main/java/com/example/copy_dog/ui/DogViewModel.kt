package com.example.copy_dog.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.copy_dog.network.DogApi
import kotlinx.coroutines.launch
import java.io.IOException

data class UiState(
    val imageList: List<String> = emptyList(),
    val isLoading: Boolean = false
)

class DogViewModel : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState(isLoading = true))
        private set

    init {
        getDogPhotos()
    }

    fun getDogPhotos() {
        viewModelScope.launch {
            uiState = UiState(isLoading = true)
            uiState = try {
                val listResult = DogApi.retrofitService.getPhotos()
                UiState(imageList = listResult.message)
            } catch (e: IOException) {
                UiState(isLoading = false)
            }
        }
    }
}