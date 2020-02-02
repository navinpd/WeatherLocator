package com.navin.downloadfiletest.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CardList(

    @Expose
    @SerializedName("cityName")
    var cityName: String,


    @Expose
    @SerializedName("timeStamp")
    var timeStamp: Long) {

    override fun toString(): String = "{\"cityName\": \"$cityName\", \"timeStamp\":\"$timeStamp\"}"

//    companion object : Comparator<LocalCityData> {
//        override fun compare(a: LocalCityData, b: LocalCityData): Int {
//
//            println("TimeStamp Comparision ${a.timeStamp} ${b.timeStamp}")
//            return (a.timeStamp - b.timeStamp) as Int
//        }
//    }
}