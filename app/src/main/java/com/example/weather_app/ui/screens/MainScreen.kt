package com.example.weather_app.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue



import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import com.example.weather_app.viewmodel.WeatherViewModel
import com.example.weather_app.repository.WeatherResponse
import com.example.weather_app.ui.components.BottomNavItem
import com.example.weather_app.ui.components.BottomNavigationBar
import com.example.weather_app.ui.components.CoordinateInput
import com.example.weather_app.ui.components.LoadingSpinner
import com.example.weather_app.ui.components.WeatherCard
import com.example.weather_app.ui.components.WeatherCardContent
import com.example.weather_app.ui.screens.InfoScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainScreen(viewModel: WeatherViewModel = viewModel()) {
    val navController = rememberNavController()
    val weatherData by viewModel.weatherData.observeAsState(initial = null)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)
    val snack_BarHostState = remember { SnackbarHostState() }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Weather App") },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Gray
                        )
            )
        },


        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem("Home", "home", Icons.Default.Home),
                    BottomNavItem("Info", "info", Icons.Default.Info)
                ),
                navController = navController,
                onItemClick = { item ->
                    navController.navigate(item.route)
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snack_BarHostState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(weatherData, viewModel::fetchWeather) }
            composable("info") { InfoScreen() }
        }
        errorMessage?.let {
            LaunchedEffect(it) {
                snack_BarHostState.showSnackbar(it)
                viewModel.clearErrorMessage()
            }
        }
    }
}

@Composable
fun HomeScreen(weatherData: WeatherResponse?, onFetchWeather: (Double, Double) -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    if (isLandscape) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CoordinateInput(
                        modifier = Modifier.weight(0.7f),
                        onSubmit = onFetchWeather
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Handle submit */ },
                        modifier = Modifier.weight(0.3f).align(Alignment.CenterVertically)
                    ) {
                        Text("Get Weather")
                    }
                }
            }
            if (weatherData != null) {
                item {
                    WeatherCard {
                        WeatherCardContent(weatherData)
                    }
                }
            } else {
                item {
                    LoadingSpinner()
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            CoordinateInput(
                modifier = Modifier.fillMaxWidth(),
                onSubmit = onFetchWeather
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (weatherData != null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        WeatherCard {
                            WeatherCardContent(weatherData)
                        }
                    }
                }
            } else {
                LoadingSpinner()
            }
        }
    }
}




