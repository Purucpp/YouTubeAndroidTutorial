package com.yesandroid.youtubeandroidtutorial.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: ApiService by lazy {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl("https://yesandroid.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) // Even if you don’t use it, safe to include
            .build()
            .create(ApiService::class.java)
    }
}
