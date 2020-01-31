package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResult (

	@Expose
	@SerializedName("data") val data : Data
)