package com.navin.downloadfiletest.data.remote.response.search_response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResults(
    @Expose
    @SerializedName("search_api")
    val search_api: Search_api
)