package com.navin.downloadfiletest.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.response.weather_response.Data
import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.ui.MainActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class CityDetailsViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService) {

    companion object {
        val TAG : String = "CityDetailsViewModel"
    }

    val getCityDetailsLiveData: MutableLiveData<Data> = MutableLiveData()

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

    fun onDestroy() {
        compositeDisposable.clear()
    }

}