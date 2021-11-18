package com.example.weatherforecastapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherforecastapp.model.DailyWeather
import com.example.weatherforecastapp.ui.theme.WeatherForecastAppTheme
import java.text.SimpleDateFormat
import java.util.*

const val WEATHER_PARAM = "WeatherParameter"
const val DAILY_WEATHER_FORECAST_TEST = "DailyWeatherForecastTest"

class WeatherDetailsActivity : ComponentActivity() {
    private val TAG = WeatherDetailsActivity::class.java.simpleName

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
                    Text(text = stringResource(R.string.weather_detail))
                })
            }
        ) {
            val dailyWeather = intent.extras?.getParcelableArrayList<DailyWeather>(WEATHER_PARAM)
            ShowWeatherDetails(dailyWeather)
        }
    }

    @Composable
    fun ShowWeatherDetails(dailyWeather: List<DailyWeather>?) {
        dailyWeather?.let { data ->
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .testTag(DAILY_WEATHER_FORECAST_TEST)
            ) {
                items(items = data) { dailyWeather ->
                    WeatherItem(dailyWeather = dailyWeather)
                }
            }
        } ?: Toast.makeText(
            this,
            stringResource(R.string.no_weather_data),
            Toast.LENGTH_LONG
        ).show()
    }

    @Composable
    fun WeatherItem(dailyWeather: DailyWeather) {
        val milliSeconds = 1000L
        val simpleDateFormat = SimpleDateFormat("EEEE, MMM dd", Locale.getDefault())
        Surface(modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp), elevation = 10.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = simpleDateFormat.format(Date(dailyWeather.dt * milliSeconds)),
                        style = MaterialTheme.typography.h6
                    )
                    Text(text = stringResource(R.string.weather), style = MaterialTheme.typography.body1)
                    Row {
                        Spacer(Modifier.size(16.dp))
                        Text(text = dailyWeather.weather[0].main)
                    }
                    Row {
                        Spacer(Modifier.size(16.dp))
                        Text(text = dailyWeather.weather[0].description)
                    }

                    Text(
                        text = stringResource(
                            R.string.day_temp,
                            dailyWeather.temp.min,
                            dailyWeather.temp.max
                        )
                    )
                    Row {
                        Spacer(Modifier.size(16.dp))
                        Column {
                            Text(text = stringResource(R.string.day, dailyWeather.temp.day))
                            Text(text = stringResource(R.string.morning, dailyWeather.temp.morn))
                            Text(text = stringResource(R.string.evening, dailyWeather.temp.eve))
                            Text(text = stringResource(R.string.night, dailyWeather.temp.night))
                        }
                    }

                    Text(text = stringResource(R.string.feels_like))
                    Row {
                        Spacer(Modifier.size(16.dp))
                        Column {
                            Text(text = stringResource(R.string.day, dailyWeather.feels_like.day))
                            Text(
                                text = stringResource(
                                    R.string.evening,
                                    dailyWeather.feels_like.eve
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.night,
                                    dailyWeather.feels_like.night
                                )
                            )
                        }
                    }
                    Text(text = stringResource(R.string.daily_pressure, dailyWeather.pressure))
                    Text(text = stringResource(R.string.daily_humidity, dailyWeather.humidity))
                    Text(text = stringResource(R.string.dew_point, dailyWeather.dew_point))
                    Text(text = stringResource(R.string.day_wind_speed, dailyWeather.wind_speed))
                    Text(text = stringResource(R.string.wind_gust, dailyWeather.wind_gust))
                    Text(text = stringResource(R.string.uv_index, dailyWeather.uvi))
                }
            }

        }
    }


}