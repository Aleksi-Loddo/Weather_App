package com.example.weather_app.repository

data class GeoCodingResponse(
    val results: List<GeocodingResult>
)

data class GeocodingResult(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String
)