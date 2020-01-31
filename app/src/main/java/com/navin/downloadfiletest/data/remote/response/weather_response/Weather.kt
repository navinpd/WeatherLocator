package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Weather(

    @Expose @SerializedName("date") val date: String,
    @Expose @SerializedName("astronomy") val astronomy: List<Astronomy>,
    @Expose @SerializedName("maxtempC") val maxtempC: Int,
    @Expose @SerializedName("maxtempF") val maxtempF: Int,
    @Expose @SerializedName("mintempC") val mintempC: Int,
    @Expose @SerializedName("mintempF") val mintempF: Int,
    @Expose @SerializedName("avgtempC") val avgtempC: Int,
    @Expose @SerializedName("avgtempF") val avgtempF: Int,
    @Expose @SerializedName("totalSnow_cm") val totalSnow_cm: Double,
    @Expose @SerializedName("sunHour") val sunHour: Double,
    @Expose @SerializedName("uvIndex") val uvIndex: Int,
    @Expose @SerializedName("hourly") val hourly: List<Hourly>
)