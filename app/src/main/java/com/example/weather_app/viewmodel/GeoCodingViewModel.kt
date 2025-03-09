package com.example.weather_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather_app.api.GeocodingApi
import com.example.weather_app.repository.GeocodingResult
import kotlinx.coroutines.launch

class GeocodingViewModel(
    private val geocodingApi: GeocodingApi
) : ViewModel() {

    private val _placeName = MutableLiveData<String>()
    val placeName: LiveData<String> get() = _placeName

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _searchResults = MutableLiveData<List<GeocodingResult>>()
    val searchResults: LiveData<List<GeocodingResult>> get() = _searchResults

    fun fetchPlaceName(name: String) {
        viewModelScope.launch {
            try {
                val response = geocodingApi.getPlaceName(name)
                if (response.results.isNotEmpty()) {
                    _searchResults.value = response.results
                } else {
                    _searchResults.value = emptyList()
                    _errorMessage.value = "No results found"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch place name: ${e.message}"
                _searchResults.value = emptyList()
            }
        }
    }
}

class GeocodingViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeocodingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GeocodingViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}