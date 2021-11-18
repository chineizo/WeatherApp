package com.example.weatherforecastapp.model

/**
 * Seal class that encapsulates the network response of the WeatherApi.
 */
sealed class WeatherStatusResponse {
    data class Success(val data: WeatherData) : WeatherStatusResponse()
    data class Failure(val exception: String?) : WeatherStatusResponse()
    class Loading(val message: String) : WeatherStatusResponse()
}