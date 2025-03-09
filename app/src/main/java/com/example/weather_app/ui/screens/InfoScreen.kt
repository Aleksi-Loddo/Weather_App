package com.example.weather_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.R

@Composable
fun InfoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.info_screen_title),
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.info_screen_description),
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.info_screen_api),
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.info_screen_developer),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}