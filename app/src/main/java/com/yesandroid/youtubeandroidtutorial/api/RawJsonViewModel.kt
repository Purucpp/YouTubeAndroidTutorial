package com.yesandroid.youtubeandroidtutorial.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class RawJsonViewModel : ViewModel() {
    private val _json = MutableStateFlow<String?>(null)
    val json: StateFlow<String?> = _json

    fun fetchJson() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getRawJson()
                _json.value = response.string() // convert ResponseBody to String
            } catch (e: IOException) {
                _json.value = "Error: ${e.message}"
            }
        }
    }
}
