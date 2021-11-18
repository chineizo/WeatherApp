package com.example.weatherforecastapp

import android.app.Application

/**
 * Class that represents the Weather Application
 */
class WeatherForecastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        getInstance = this
    }

    companion object {
        //Singleton instance of the application
        lateinit var getInstance: WeatherForecastApplication
            private set
    }
}