package com.navin.downloadfiletest.ui.fragment

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.response.weather_response.Data
import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.ui.MainActivity
import com.navin.downloadfiletest.utils.CardList
import com.navin.downloadfiletest.utils.LOCAL_LIST
import com.navin.downloadfiletest.utils.LocalCityArray
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class CityDetailsViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService,
    private val sharedPreferences: SharedPreferences) {

    val getCityDetailsLiveData: MutableLiveData<Data> = MutableLiveData()

    companion object {
        val TAG : String = "CityDetailsViewModel"
    }


    fun queryCityDetails(query: String) {
        compositeDisposable.add(
            networkService.getWeatherDetails(queryText = query)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        getCityDetailsLiveData.postValue(it.data)
                    },
                    {
                        Log.d(TAG, it.toString())
                    }
                )
        )
    }

    //Logic to save searched city details in local
    fun saveToLocal(city: String) {
        val currentCityData = CardList(city, System.currentTimeMillis())

        var savedCityList = LocalCityArray(mutableListOf())
        var finalCityData: String?

        if (sharedPreferences.getString(LOCAL_LIST, "")!!.isNotEmpty()) {
            savedCityList = Gson()
                .fromJson<LocalCityArray>(
                    sharedPreferences.getString(LOCAL_LIST, ""),
                    LocalCityArray::class.java
                )
        }

        var cityIfPresent: CardList? = null

        when (savedCityList.cardList.size) {

            0 -> {
                finalCityData = "{\"CardList\":[${currentCityData}]}"

            }
            else -> {
                savedCityList.cardList.forEach {
                    if (it.cityName == city) {
                        cityIfPresent = it
                    }
                }

                // Remove city name if it's already present
                cityIfPresent?.let {
                    savedCityList.cardList.remove(cityIfPresent!!)
                }

                savedCityList.cardList.add(0, currentCityData)
                finalCityData = savedCityList.toString()
            }
        }

        if (savedCityList.cardList.size > 10) {

            savedCityList.cardList.removeAt(10)
            finalCityData = savedCityList.toString()
        }

        Log.d(CityDetailsFragment.TAG, "City Details is $finalCityData")

        sharedPreferences.edit().putString(LOCAL_LIST, finalCityData).apply()


        sharedPreferences.edit().putString(city, finalCityData).apply()
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

}
