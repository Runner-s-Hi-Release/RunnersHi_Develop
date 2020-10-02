package com.example.runnershi_develop.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer{
        private const val BASE_URL = "http://"
        private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val service: RequestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestInterface::class.java)
}