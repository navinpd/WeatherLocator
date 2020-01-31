package com.navin.downloadfiletest.data.remote.response.search_response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Search_api (

	@Expose
	@SerializedName("result") val result : List<Result>
)