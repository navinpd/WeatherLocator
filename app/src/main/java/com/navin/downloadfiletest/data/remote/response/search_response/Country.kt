package com.navin.downloadfiletest.data.remote.response.search_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country (

	@Expose
	@SerializedName("__cdata") val __cdata : String
)