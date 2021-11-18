package com.example.weatherforecastapp.repository

import com.example.weatherforecastapp.model.WeatherData
import com.example.weatherforecastapp.service.WeatherServiceApi

/**
 * Repository Class that retrieves Weather Data from the API
 */
class WeatherRepository(private val weatherServiceApi: WeatherServiceApi) {

    /**
     * Function to retrieve Weather Data from the API
     * @param latitude Latitude value as a String
     * @param longitude Longitude value as a String
     */
    suspend fun getWeatherData(latitude: String, longitude: String): WeatherData {
        return weatherServiceApi.getWeatherForecast(latitude, longitude)
    }
}