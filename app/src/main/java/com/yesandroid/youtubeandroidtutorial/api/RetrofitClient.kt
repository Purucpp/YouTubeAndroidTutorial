package com.yesandroid.youtubeandroidtutorial.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://yesandroid.com")
            .addConverterFactory(GsonConverterFactory.create()) // Even if you don’t use it, safe to include
            .build()
            .create(ApiService::class.java)
    }
}
