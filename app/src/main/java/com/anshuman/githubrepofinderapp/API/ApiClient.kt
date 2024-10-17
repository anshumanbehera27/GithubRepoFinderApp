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
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(ApiInterface::class.java)
    }
}
