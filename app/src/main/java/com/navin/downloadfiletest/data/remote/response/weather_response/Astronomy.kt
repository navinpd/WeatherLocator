package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Astronomy (

	@Expose @SerializedName("sunrise") val sunrise : String,
	@Expose @SerializedName("sunset") val sunset : String,
	@Expose @SerializedName("moonrise") val moonrise : String,
	@Expose @SerializedName("moonset") val moonset : String,
	@Expose @SerializedName("moon_phase") val moon_phase : String,
	@Expose @SerializedName("moon_illumination") val moon_illumination : Int
)