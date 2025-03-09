package com.example.weather_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal =16.dp,),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Info Screen",
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 10.dp, ),
            textAlign = TextAlign.Center
        )
        Text(
            text = "This is an Android app to check the weather using latitude and longitude coordinates of citys.",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = "The app uses Open-meteo API available at https://open-meteo.com/",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Developed by Aleksi Loddo",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp),
        )
    }
}
