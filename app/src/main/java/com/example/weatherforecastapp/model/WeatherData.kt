package com.example.weatherforecastapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Model that describes the Weather entity
 */
@Parcelize
data class WeatherData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val current: CurrentWeather,
    val daily: ArrayList<DailyWeather>
) : Parcelable

/**
 * Data class for the Current Weather
 */
@Parcelize
data class CurrentWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val daily: List<Weather>
) : Parcelable

/**
 * Data class for Daily Weather
 */
@Parcelize
data class DailyWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moon_phase: Double,
    val temp: Temp,
    val feels_like: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    val weather: List<Weather>,
    val clouds: Int,
    val pop: Double,
    val uvi: Double
) : Parcelable

@Parcelize
data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable

/**
 * Data class for FeelsLike information
 */
@Parcelize
data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) :
    Parcelable

/**
 * Data class of Weather
 */
@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
