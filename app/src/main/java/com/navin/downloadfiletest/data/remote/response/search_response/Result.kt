package com.navin.downloadfiletest.data.remote.response.search_response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Result(

    @Expose
    @SerializedName("areaName") val areaName: AreaName,


    @Expose
    @SerializedName("country") val country: Country,

    @Expose
    @SerializedName("region") val region: Region,


    @Expose
    @SerializedName("latitude") val latitude: Double,

    @Expose
    @SerializedName("longitude") val longitude: Double,

    @Expose
    @SerializedName("population") val population: Int,

    @Expose
    @SerializedName("weatherUrl") val weatherUrl: WeatherUrl
)