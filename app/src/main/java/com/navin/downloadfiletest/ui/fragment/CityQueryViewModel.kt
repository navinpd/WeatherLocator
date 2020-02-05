package com.navin.downloadfiletest.ui.fragment

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.response.search_response.SearchResults
import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.utils.LOCAL_LIST
import com.navin.downloadfiletest.utils.LocalCityArray
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class CityQueryViewModel  @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService,
    private val sharedPreferences: SharedPreferences) {

    companion object {
        val TAG : String = "CityQueryViewModel"
    }

    val getSearchResults = MutableLiveData<SearchResults>()
    val getLocalSavedData = MutableLiveData<MutableList<String>>()

    fun getSearchResult(query: String) {

        compositeDisposable.add(
            networkService.doSearchCity(queryText = query, numOfResults = 4)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        getSearchResults.postValue(it)
                        Log.d(TAG, it.toString())
                    },
                    {
                        getSearchResults.postValue(null)
                        Log.d(TAG, it.toString())
                    }
                )
        )
    }

    fun updateLocallySavedList() {
        val localList = sharedPreferences.getString(LOCAL_LIST, "")
        println("Locally saved data are $localList")

        var listOfSelectableCity = mutableListOf<String>()
        if (localList!!.isNotEmpty()) {
            val localCityArray: LocalCityArray = Gson()
                .fromJson<LocalCityArray>(
                    localList,
                    LocalCityArray::class.java
                )
            localCityArray.cityData
                .sortedWith(compareBy { it.timeStamp })

            localCityArray.cityData.forEach {
                listOfSelectableCity.add(it.cityName)
            }
        }

        getLocalSavedData.postValue(listOfSelectableCity)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

}
