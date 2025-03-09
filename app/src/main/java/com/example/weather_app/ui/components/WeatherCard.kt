package com.example.weather_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.repository.WeatherResponse

@Composable
fun WeatherCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            content()
        }
    }
}

@Composable
fun WeatherCardContent(weatherData: WeatherResponse) {
    val daylightDurationMinutes = weatherData.daily.daylight_duration[0]
    val hours = daylightDurationMinutes / 60
    val minutes = daylightDurationMinutes % 60

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Max Temperature: ${weatherData.daily.temperature_2m_max[0]}°C",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Min Temperature: ${weatherData.daily.temperature_2m_min[0]}°C",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Apparent Max Temperature: ${weatherData.daily.apparent_temperature_max[0]}°C",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Apparent Min Temperature: ${weatherData.daily.apparent_temperature_min[0]}°C",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        //Text(
         //   text = "Daylight Duration: ${hours}h ${minutes}m",
         //   fontSize = 20.sp,
         //   fontWeight = FontWeight.Bold,
        //  modifier = Modifier.padding(bottom = 8.dp)
       // )
        Text(
            text = "Max UV Index: ${weatherData.daily.uv_index_max[0]}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Max Precipitation Probability: ${weatherData.daily.precipitation_probability_max[0]}%",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}