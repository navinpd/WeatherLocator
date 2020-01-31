package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Current_condition(

    @Expose @SerializedName("observation_time") val observation_time: String,
    @Expose @SerializedName("temp_C") val temp_C: Int,
    @Expose @SerializedName("temp_F") val temp_F: Int,
    @Expose @SerializedName("weatherCode") val weatherCode: Int,
    @Expose @SerializedName("weatherIconUrl") val weatherIconUrl: List<WeatherIconUrl>,
    @Expose @SerializedName("weatherDesc") val weatherDesc: List<WeatherDesc>,
    @Expose @SerializedName("windspeedMiles") val windspeedMiles: Int,
    @Expose @SerializedName("windspeedKmph") val windspeedKmph: Int,
    @Expose @SerializedName("winddirDegree") val winddirDegree: Int,
    @Expose @SerializedName("winddir16Point") val winddir16Point: String,
    @Expose @SerializedName("precipMM") val precipMM: Double,
    @Expose @SerializedName("precipInches") val precipInches: Double,
    @Expose @SerializedName("humidity") val humidity: Int,
    @Expose @SerializedName("visibility") val visibility: Int,
    @Expose @SerializedName("visibilityMiles") val visibilityMiles: Int,
    @Expose @SerializedName("pressure") val pressure: Int,
    @Expose @SerializedName("pressureInches") val pressureInches: Int,
    @Expose @SerializedName("cloudcover") val cloudcover: Int,
    @Expose @SerializedName("FeelsLikeC") val feelsLikeC: Int,
    @Expose @SerializedName("FeelsLikeF") val feelsLikeF: Int,
    @Expose @SerializedName("uvIndex") val uvIndex: Int
)