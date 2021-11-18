package com.example.weatherforecastapp.repository

import com.example.weatherforecastapp.model.WeatherData
import com.example.weatherforecastapp.service.WeatherServiceApi

class WeatherRepository(private val weatherServiceApi: WeatherServiceApi) {

    suspend fun getWeatherData(latitude: String, longitude: String): WeatherData {
        return weatherServiceApi.getWeatherForecast(latitude, longitude)
    }
}