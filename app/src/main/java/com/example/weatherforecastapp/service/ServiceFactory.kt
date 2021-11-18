package com.example.weatherforecastapp.service

import com.example.weatherforecastapp.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that builds the Retrofit client for API requests.
 */
object ServiceFactory {
    inline fun <reified T> createService(): T =
        retrofit(baseUrl = AppConstants.BASE_URL).create(T::class.java)

    /**
     * Method that returns a Retrofit instance
     */
    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp())
        .build()

    /**
     * Builder method to return OkHttpClient
     */
    private fun okHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return builder.build()
    }
}