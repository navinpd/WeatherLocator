package com.navin.downloadfiletest.data.remote.response.weather_response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Hourly (

	@Expose @SerializedName("time") val time : Int,
	@Expose @SerializedName("tempC") val tempC : Int,
	@Expose @SerializedName("tempF") val tempF : Int,
	@Expose @SerializedName("windspeedMiles") val windspeedMiles : Int,
	@Expose @SerializedName("windspeedKmph") val windspeedKmph : Int,
	@Expose @SerializedName("winddirDegree") val winddirDegree : Int,
	@Expose @SerializedName("winddir16Point") val winddir16Point : String,
	@Expose @SerializedName("weatherCode") val weatherCode : Int,
	@Expose @SerializedName("weatherIconUrl") val weatherIconUrl : List<WeatherIconUrl>,
	@Expose @SerializedName("weatherDesc") val weatherDesc : List<WeatherDesc>,
	@Expose @SerializedName("precipMM") val precipMM : Double,
	@Expose @SerializedName("precipInches") val precipInches : Double,
	@Expose @SerializedName("humidity") val humidity : Int,
	@Expose @SerializedName("visibility") val visibility : Int,
	@Expose @SerializedName("visibilityMiles") val visibilityMiles : Int,
	@Expose @SerializedName("pressure") val pressure : Int,
	@Expose @SerializedName("pressureInches") val pressureInches : Int,
	@Expose @SerializedName("cloudcover") val cloudcover : Int,
	@Expose @SerializedName("HeatIndexC") val heatIndexC : Int,
	@Expose @SerializedName("HeatIndexF") val heatIndexF : Int,
	@Expose @SerializedName("DewPointC") val dewPointC : Int,
	@Expose @SerializedName("DewPointF") val dewPointF : Int,
	@Expose @SerializedName("WindChillC") val windChillC : Int,
	@Expose @SerializedName("WindChillF") val windChillF : Int,
	@Expose @SerializedName("WindGustMiles") val windGustMiles : Int,
	@Expose @SerializedName("WindGustKmph") val windGustKmph : Int,
	@Expose @SerializedName("FeelsLikeC") val feelsLikeC : Int,
	@Expose @SerializedName("FeelsLikeF") val feelsLikeF : Int,
	@Expose @SerializedName("chanceofrain") val chanceofrain : Int,
	@Expose @SerializedName("chanceofremdry") val chanceofremdry : Int,
	@Expose @SerializedName("chanceofwindy") val chanceofwindy : Int,
	@Expose @SerializedName("chanceofovercast") val chanceofovercast : Int,
	@Expose @SerializedName("chanceofsunshine") val chanceofsunshine : Int,
	@Expose @SerializedName("chanceoffrost") val chanceoffrost : Int,
	@Expose @SerializedName("chanceofhightemp") val chanceofhightemp : Int,
	@Expose @SerializedName("chanceoffog") val chanceoffog : Int,
	@Expose @SerializedName("chanceofsnow") val chanceofsnow : Int,
	@Expose @SerializedName("chanceofthunder") val chanceofthunder : Int,
	@Expose @SerializedName("uvIndex") val uvIndex : Int
)