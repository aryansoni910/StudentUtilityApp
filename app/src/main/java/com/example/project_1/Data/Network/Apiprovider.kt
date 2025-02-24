package com.example.project_1.Data.Network

import com.example.project_1.Common.Base_url
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apiprovider {
    fun provideApi() = Retrofit.Builder().baseUrl(Base_url)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}