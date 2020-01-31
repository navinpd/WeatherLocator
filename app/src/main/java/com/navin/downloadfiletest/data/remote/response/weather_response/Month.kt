package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.SerializedName


data class Month(

    @SerializedName("index") val index: Int,
    @SerializedName("name") val name: String,
    @SerializedName("avgMinTemp") val avgMinTemp: Double,
    @SerializedName("avgMinTemp_F") val avgMinTemp_F: Double,
    @SerializedName("absMaxTemp") val absMaxTemp: Double,
    @SerializedName("absMaxTemp_F") val absMaxTemp_F: Double,
    @SerializedName("avgDailyRainfall") val avgDailyRainfall: Double
)