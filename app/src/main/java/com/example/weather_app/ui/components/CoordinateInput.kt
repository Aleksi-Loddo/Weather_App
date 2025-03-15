package com.example.weather_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
fun CoordinateInput(
    modifier: Modifier = Modifier,
    initialLatitude: String = "65.0124",
    initialLongitude: String = "25.4682",
    onSubmit: (Double, Double) -> Unit
    ) {
        var latitude by remember { mutableStateOf(initialLatitude) }
        var longitude by remember { mutableStateOf(initialLongitude) }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {

                val lat = latitude.toDoubleOrNull()
                val lon = longitude.toDoubleOrNull()

                if (lat != null && lon != null) {
                    onSubmit(lat, lon)
                }
                if (lat != null && lon != null) {
                        if (lat > 180 || lat < -180 || lon > 180 || lon < -180) {
                            return@Button
                        }

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Weather")
        }
    }
}