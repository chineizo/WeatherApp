package com.example.weatherforecastapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherforecastapp.TestCoroutineRule
import com.example.weatherforecastapp.model.WeatherStatusResponse
import com.example.weatherforecastapp.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MainActivityViewModel

    @Mock
    private lateinit var observer: Observer<WeatherStatusResponse>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this);
        viewModel = MainActivityViewModel(mock(WeatherRepository::class.java))
    }

    @Test
    fun getWeatherData() {
        viewModel.getWeatherData(anyString(), anyString()).observeForever(observer)
    }
}