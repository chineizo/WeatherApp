package com.example.weatherforecastapp.service

import com.example.weatherforecastapp.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface that defines the API endpoints to retrive Weather Data
 */
private const val key = "59ac41458256ef1fc2ebfddda1ded2da"
interface WeatherServiceApi {
    @GET("data/2.5/onecall?appid=$key&exclude=alerts,minutely&units=imperial")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): WeatherData
}