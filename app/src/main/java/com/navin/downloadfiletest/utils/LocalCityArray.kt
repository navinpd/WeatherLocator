package com.navin.downloadfiletest.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocalCityArray (
    @Expose
    @SerializedName("CityData")
    val cityData : MutableList<CityData>
) {

    override fun toString(): String {
        return "{\"CityData\":$cityData}"
    }

//{
//  "CityData": [
//    {
//      "cityName": "Jharia-India ",
//      "timeStamp": "1580640497435"
//    }
//  ]
//}
}