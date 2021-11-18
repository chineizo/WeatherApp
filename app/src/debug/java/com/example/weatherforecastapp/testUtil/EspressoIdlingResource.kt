package com.example.weatherforecastapp.testUtil

import androidx.test.espresso.idling.CountingIdlingResource
/**
 * Idling Resource to handle asynchronous events to support accurate Instrumentation test
 */
object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}