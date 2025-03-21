package com.example.weather_app.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weather_app.repository.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getDailyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,uv_index_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_probability_max",
        @Query("timezone") timezone: String = "auto",
        @Query("forecast_days") forecastDays: Int = 1
    ): WeatherResponse

    companion object {
        fun create(): WeatherApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(WeatherApi::class.java)
        }
    }
}

