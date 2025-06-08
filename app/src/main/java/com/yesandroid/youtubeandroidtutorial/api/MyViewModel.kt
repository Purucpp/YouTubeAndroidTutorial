package com.yesandroid.youtubeandroidtutorial.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MyViewModel : ViewModel() {
    private val _apiResult = MutableStateFlow<String?>(null)
    val apiResult: StateFlow<String?> = _apiResult

    fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getRawJson() // Use your actual method
                _apiResult.value = response.string() // If using ResponseBody
            } catch (e: IOException) {
                _apiResult.value = "Error: ${e.message}"
            }
        }
    }
}
