package com.example.weatherforecastapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherforecastapp.testUtil.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun cleanUp() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testThatWeatherApplicationIsVisible() {
        val pageTitle = composeTestRule.activity.getString(R.string.app_name)
        composeTestRule.onNodeWithText(pageTitle).assertExists()
    }

    @Test
    fun testThatTheSevenDayForecastActivityIsLaunchedAfterButtonClick () {
        val seeWeatherDetailsTitle = composeTestRule.activity.getString(R.string.see_weather_details)
        composeTestRule.onNodeWithText(seeWeatherDetailsTitle).performClick()

        val weatherDetail = composeTestRule.activity.getString(R.string.weather_detail)
        composeTestRule.onNodeWithText(weatherDetail).assertIsDisplayed()


    }
}