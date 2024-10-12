package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    private val _weather = MutableLiveData<WeatherResponse?>()
    val weather: LiveData<WeatherResponse?> get() = _weather

    fun fetchRepoDetails(latitude: Double, longitude: Double) {
        val call = RetrofitInstance.apiServices.getCurrentWeather(latitude, longitude)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    _weather.postValue(response.body())
                } else {
                    // Handle error, log error or set a default value in LiveData
                    _weather.postValue(null)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure (e.g., no network, timeout, etc.)
                _weather.postValue(null)
            }
        })
    }
}
//    var weather: WeatherResponse? = null
//
//    fun fetchRepoDetails(latitude: Double, longitude: Double) {
//        val call = RetrofitInstance.apiServices.getCurrentWeather(latitude, longitude)
//        call.enqueue(object : Callback<WeatherResponse> {
//            override fun onResponse(
//                call: Call<WeatherResponse>,
//                response: Response<WeatherResponse>
//            ) {
//                if (response.isSuccessful) {
//                    if (weather != null) {
//                        // Handle the repo data, update UI with name, full name, owner's avatar, etc.
//                        weather = response.body()
//                    } else {
//                        // Handle case where the repository is null
//                    }
//                } else {
//                    // Handle non-successful response
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//                // Handle failure (e.g., no network, timeout, etc.)
//            }
//        })
//    }
