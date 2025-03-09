package com.example.weather_app.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather_app.api.GeocodingApi
import com.example.weather_app.api.WeatherApi
import com.example.weather_app.repository.WeatherResponse
import kotlinx.coroutines.launch

class WeatherViewModel(
        private val weatherApi: WeatherApi,
) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> get() = _weatherData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getDailyWeather(latitude, longitude)
                _weatherData.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch weather data: ${e.message}"
                _weatherData.value = null
            }
        }
    }
}

class WeatherViewModelFactory(private val weatherApi: WeatherApi) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}