package com.example.weatherforecastapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.weatherforecastapp.model.WeatherStatusResponse
import com.example.weatherforecastapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers.IO
import java.lang.IllegalArgumentException

/**
 * View Model that offers logic to retrieve Weather Data from the Weather API
 */
class MainActivityViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    /**
     * A method to fetch the WeatherData from the WeatherAPI
     */
    fun getWeatherData(latitude: String, longitude: String) = liveData(IO) {
        try {
            emit(
                WeatherStatusResponse.Success(
                    weatherRepository.getWeatherData(
                        latitude,
                        longitude
                    )
                )
            )
        } catch (exception: Exception) {
            emit(WeatherStatusResponse.Failure(exception.message))
        }
    }
}

/**
 * Factory class to produce an instance of MainActivityViewModel
 */
internal class MainActivityViewModelFactory(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown class name seen")
    }
}