package com.navin.downloadfiletest.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LocalCityArray (
    @Expose
    @SerializedName("CardList")
    val cardList : MutableList<CardList>
) {

    override fun toString(): String {
        return "{\"CardList\":$cardList}"
    }

//{
//  "CardList": [
//    {
//      "cityName": "Jharia-India ",
//      "timeStamp": "1580640497435"
//    }
//  ]
//}
}