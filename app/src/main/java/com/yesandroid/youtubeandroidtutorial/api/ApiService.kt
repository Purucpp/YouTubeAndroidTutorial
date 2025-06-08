package com.yesandroid.youtubeandroidtutorial.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {
    @GET("/api/kt.json") // Replace with your actual endpoint
    suspend fun getRawJson(): ResponseBody
}
