package com.example.admin_buyit.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FetchProduct {
    private const val url = "https://fakestoreapi.com/"

    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun<T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}