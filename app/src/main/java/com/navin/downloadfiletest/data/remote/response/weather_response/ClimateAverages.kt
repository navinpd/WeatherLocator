package com.navin.downloadfiletest.data.remote.response.weather_response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClimateAverages(
    @Expose
    @SerializedName("month") val month: List<Month>
)