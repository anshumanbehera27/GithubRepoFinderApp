package com.anshuman.githubrepofinderapp.API

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {

    private const val BASE_URL = "https://api.github.com/"

    fun getClient(): ApiInterface {
        // Initialize OkHttpClient with optional network interceptor for debugging
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor()) // Optional for debugging
            .build()

        // Initialize Moshi with KotlinJsonAdapterFactory for Kotlin support
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        // Set up Retrofit with base URL, Moshi converter, and OkHttp client
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(ApiInterface::class.java)
    }
}
