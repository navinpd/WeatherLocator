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

}