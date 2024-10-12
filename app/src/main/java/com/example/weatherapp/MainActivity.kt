package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {

    val viewModel: WeatherViewModel by viewModels()
    var latitude: Double? = null
    var longitude: Double? = null
    var city: String? = null
    var selectedLatitude: Double? = null
    var selectedLongitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cityName = cityList.map { it.name }
        val btn = findViewById<ImageView>(R.id.searchBTN)
        val cityText = findViewById<TextView>(R.id.cityTextView)
        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.loadingProgressBar)
        val autoSelect = findViewById<AutoCompleteTextView>(R.id.actCities)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cityName)
        autoSelect.setAdapter(adapter)



        btn.setOnClickListener {

            city = autoSelect.text.toString()

            val selectedCity = cityList.find { it.name == city }

            if(isCityInList(city!!, cityList)){
            selectedLatitude = selectedCity?.latitude
            selectedLongitude = selectedCity?.longitude


            lottieAnimationView.visibility = View.VISIBLE
            lottieAnimationView.playAnimation()

            viewModel.fetchRepoDetails(selectedLatitude!!, selectedLongitude!!)

            viewModel.weather.observe(this, Observer { weatherResponse ->

                if (weatherResponse != null) {
                    // Update UI with weather data
                    findViewById<TextView>(R.id.temperatureTextView).text =
                        "${weatherResponse.current_weather.temperature}°C"
                    findViewById<TextView>(R.id.weatherDescriptionTextView).text =
                        "Wind Speed: ${weatherResponse.current_weather.windspeed} m/s"
                    findViewById<ImageView>(R.id.weatherIconImageView).setImageResource(
                        getWeatherIconResource(weatherResponse.current_weather.weathercode)
                    )
                } else {
                    // Handle case where weather data is null (e.g., show error message)
                    findViewById<TextView>(R.id.temperatureTextView).text = "No data available"
                }
            })

            // Set city name (Lahore)
            cityText.text = city

            Handler(Looper.getMainLooper()).postDelayed(
                {
                    lottieAnimationView.visibility = View.GONE
                    lottieAnimationView.cancelAnimation()
                }, 5000
            )
        }else{
            Toast.makeText(this, "City Not Found", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getWeatherIconResource(weatherCode: Int?): Int {
        // Logic to get the correct weather icon drawable resource based on weather code
        return when (weatherCode) {
            in 0..2 -> R.drawable.ic_sun // Example codes for sunny weather
            in 3..5 -> R.drawable.ic_cloud // Example codes for cloudy weather
            in 6..8 -> R.drawable.ic_night // Example codes for night time or mostly cloudy
            else -> R.drawable.ic_launcher_background // Fallback icon for unknown codes
        }
    }

    fun isCityInList(enteredText: String, cities: List<City>): Boolean {
        return cities.any { it.name.equals(enteredText, ignoreCase = true) }
    }

}