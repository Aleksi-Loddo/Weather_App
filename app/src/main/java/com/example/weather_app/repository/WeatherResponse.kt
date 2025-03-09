package com.example.weather_app.repository

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val daily: Daily
)

data class Daily(
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val apparent_temperature_max: List<Double>,
    val apparent_temperature_min: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val daylight_duration: List<Double>,
    val uv_index_max: List<Double>,
    val precipitation_probability_max: List<Double>,
    val wind_speed_10m_max: List<Double>
)