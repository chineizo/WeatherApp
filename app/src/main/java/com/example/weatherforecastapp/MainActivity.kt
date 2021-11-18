package com.example.weatherforecastapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.model.LocationSource
import com.example.weatherforecastapp.model.WeatherData
import com.example.weatherforecastapp.model.WeatherStatusResponse
import com.example.weatherforecastapp.testUtil.EspressoIdlingResource
import com.example.weatherforecastapp.ui.theme.WeatherForecastAppTheme
import com.example.weatherforecastapp.viewmodel.MainActivityViewModel
import com.example.weatherforecastapp.viewmodel.MainActivityViewModelFactory

/**
 * Class the shows the Weather forecast of New York
 */
class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val mainActivityViewModel by viewModels<MainActivityViewModel> {
        MainActivityViewModelFactory(ApplicationData.weatherRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShowAppBar()
                }
            }
        }
    }

    /**
     * A method that shows the Top App bar with title
     */
    @Composable
    fun ShowAppBar() {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = stringResource(R.string.app_name))
                })
            }
        ) {

            GetWeatherData(
                mainActivityViewModel,
                LocationSource.latitude,
                LocationSource.longitude
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        WeatherForecastAppTheme {
            ShowAppBar()
        }
    }

    @Composable
    private fun GetWeatherData(
        viewModel: MainActivityViewModel,
        latitude: String,
        longitude: String
    ) {

        val weatherStatusResponse: WeatherStatusResponse by viewModel.getWeatherData(
            latitude,
            longitude
        )
            .observeAsState(
                WeatherStatusResponse.Loading(
                    stringResource(id = R.string.loading_weather)
                )
            )
        when (weatherStatusResponse) {
            is WeatherStatusResponse.Success -> {
                val weatherData = (weatherStatusResponse as WeatherStatusResponse.Success).data
                Log.d(TAG, "Weather Data = $weatherData")
                ShowWeatherData(weatherData) {
                    launchDetailsActivity(weatherData)

                }
            }
            is WeatherStatusResponse.Failure -> {
                val error = (weatherStatusResponse as WeatherStatusResponse.Failure).exception
                Log.e(TAG, "Some Error $error")
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                EspressoIdlingResource.decrement()
            }
            is WeatherStatusResponse.Loading -> {
                val message = (weatherStatusResponse as WeatherStatusResponse.Loading).message
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                EspressoIdlingResource.increment()
            }
        }


    }

    /**
     * Display the Review Details Activity of the Coffee Shop
     */
    private fun launchDetailsActivity(weatherData: WeatherData) {
        val intent = Intent(this, WeatherDetailsActivity::class.java).apply {
            putParcelableArrayListExtra(WEATHER_PARAM, weatherData.daily)
        }
        startActivity(intent)
    }

    @Composable
    private fun ShowWeatherData(
        weatherData: WeatherData,
        onButtonClick: (weatherData: WeatherData) -> Unit
    ) {
        Column {

            Text(
                text = stringResource(R.string.location),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = weatherData.timezone,
                style = MaterialTheme.typography.h4
            )

            Text(
                text = stringResource(R.string.temperature),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = weatherData.current.temp.toString(),
                style = MaterialTheme.typography.h4
            )

            Text(
                text = stringResource(R.string.feels_like),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.today_feels_like, weatherData.current.feelsLike),
                style = MaterialTheme.typography.h6
            )


            Text(text = stringResource(R.string.humidity), style = MaterialTheme.typography.h6)
            Spacer(Modifier.size(16.dp))
            Text(
                text = weatherData.current.humidity.toString(),
                style = MaterialTheme.typography.h4
            )

            Text(
                text = stringResource(R.string.wind_speed_direction),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.size(16.dp))
            Text(
                style = MaterialTheme.typography.h4,
                text = stringResource(
                    id = R.string.wind_speed,
                    formatArgs = arrayOf(
                        weatherData.current.wind_speed,
                        weatherData.current.wind_deg
                    )
                )
            )

            Text(
                text = stringResource(R.string.atmospheric_pressure),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = weatherData.current.pressure.toString(),
                style = MaterialTheme.typography.h4
            )

            Button(
                onClick = { onButtonClick(weatherData) },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.see_weather_details))
            }
        }
    }
}



