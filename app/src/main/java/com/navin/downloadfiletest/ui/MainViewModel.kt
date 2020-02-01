package com.navin.downloadfiletest.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.response.search_response.Search_api
import com.navin.downloadfiletest.di.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
) : ViewModel() {

//    val getSearchResults = MutableLiveData<Search_api>()

//    fun getSearchResult(query: String) {
//        compositeDisposable.add(
////            networkService.doSearchCity(queryText = query, numOfResults = 3)
////                .subscribeOn(Schedulers.io())
////                .subscribe(
////                    {
////                        getSearchResults.postValue(it.search_api)
////                    },
////                    {
////                        Log.d(MainActivity.TAG, it.toString())
////                    }
////                )
////            networkService.getWeatherDetails(queryText = query)
////                .subscribeOn(Schedulers.io())
////                .subscribe(
////                    {
////                        //                        getSearchResults.postValue(it.data)
////                    },
////                    {
////                        Log.d(MainActivity.TAG, it.toString())
////                    }
////                )
//        )
//    }

}