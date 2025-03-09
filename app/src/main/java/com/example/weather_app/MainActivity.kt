package com.example.weather_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather_app.api.WeatherApi
import com.example.weather_app.ui.screens.InfoScreen
import com.example.weather_app.ui.screens.MainScreen
import com.example.weather_app.ui.theme.weather_AppTheme
import com.example.weather_app.viewmodel.WeatherViewModel
import com.example.weather_app.viewmodel.WeatherViewModelFactory


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherApi = WeatherApi.create() // Assuming you have a method to create WeatherApi instance
        val factory = WeatherViewModelFactory(weatherApi)
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)

        setContent {
            weather_AppTheme {
                WeatherApp(viewModel)
            }
        }
    }
}

@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(viewModel) }
        composable("info") { InfoScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    weather_AppTheme {
        WeatherApp(viewModel = WeatherViewModel(WeatherApi.create()))
    }
}