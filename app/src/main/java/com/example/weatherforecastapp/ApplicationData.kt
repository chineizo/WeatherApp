package com.example.weatherforecastapp

import com.example.weatherforecastapp.repository.WeatherRepository
import com.example.weatherforecastapp.service.ServiceFactory

object ApplicationData {
    val weatherRepository = WeatherRepository (ServiceFactory.createService())
}