package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Data(

    @Expose
    @SerializedName("request") val request: List<Request>,

    @Expose
    @SerializedName("current_condition") val current_condition: List<Current_condition>,

    @Expose
    @SerializedName("weather") val weather: List<Weather>,

    @Expose
    @SerializedName("ClimateAverages") val climateAverages: List<ClimateAverages>
)