package com.navin.downloadfiletest.ui.fragment

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.navin.downloadfiletest.data.remote.NetworkHelper
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.response.weather_response.Data
import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.ui.base.BaseViewModel
import com.navin.downloadfiletest.utils.CityData
import com.navin.downloadfiletest.utils.LOCAL_LIST
import com.navin.downloadfiletest.utils.LocalCityArray
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * {@link CityDetailsFragment} Supporting ViewModel to get City weather details.
 * Also saving the city name into sharePreference for user to see recently searched city
 */
@FragmentScope
class CityDetailsViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    val networkService: NetworkService,
    val sharedPreferences: SharedPreferences,
    networkHelper: NetworkHelper
) : BaseViewModel(networkHelper, compositeDisposable) {

    val getCityDetailsLiveData: MutableLiveData<Data> = MutableLiveData()
    val noInternetLiveData: MutableLiveData<String> = MutableLiveData()
    val maxLocalAllowedCity = 10

    val TAG = this.javaClass.simpleName

    /**
     * Method to get city from user selection to get from weather server.
     * @param query cityName user is interested for
     */
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
                        noInternetLiveData.postValue(it.toString())
                    }
                )
        )
    }

    /**
     * Logic to save searched city details in local
     * @param city: save the name of city user has already visited in sharedPreference
     */
    fun saveToLocal(city: String) {
        if (city.isEmpty()) return

        val currentCityData = CityData(city, System.currentTimeMillis())

        var savedCityList = LocalCityArray(mutableListOf())

        var finalCityData: String?

        if (sharedPreferences.getString(LOCAL_LIST, "")!!.isNotEmpty()) {
            savedCityList = Gson()
                .fromJson<LocalCityArray>(
                    sharedPreferences.getString(LOCAL_LIST, ""),
                    LocalCityArray::class.java
                )
        }

        var cityIfPresent: CityData? = null

        when (savedCityList.cityData.size) {

            0 -> {
                finalCityData = "{\"CityData\":[${currentCityData}]}"

            }
            else -> {
                savedCityList.cityData.forEach {
                    if (it.cityName == city) {
                        cityIfPresent = it
                    }
                }

                // Remove city name if it's already been visited earlier
                cityIfPresent?.let {
                    savedCityList.cityData.remove(cityIfPresent!!)
                }

                savedCityList.cityData.add(0, currentCityData)
                finalCityData = savedCityList.toString()
            }
        }

        if (savedCityList.cityData.size > maxLocalAllowedCity) {

            savedCityList.cityData.removeAt(maxLocalAllowedCity)
            finalCityData = savedCityList.toString()
        }

        Log.d(TAG, "City Details is $finalCityData")

        sharedPreferences.edit().putString(LOCAL_LIST, finalCityData).apply()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
