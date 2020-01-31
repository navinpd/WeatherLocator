package com.navin.downloadfiletest.data.remote.response.weather_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Request(

    @Expose
    @SerializedName("type") val type: String,

    @Expose
    @SerializedName("query") val query: String
)