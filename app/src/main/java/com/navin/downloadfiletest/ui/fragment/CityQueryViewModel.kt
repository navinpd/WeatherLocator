package com.navin.downloadfiletest.ui.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.response.search_response.SearchResults
import com.navin.downloadfiletest.data.remote.response.search_response.Search_api
import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.ui.MainActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class CityQueryViewModel  @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService) {

    companion object {
        val TAG : String = "CityQueryViewModel"
    }

    val getSearchResults = MutableLiveData<SearchResults>()

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

    fun onDestroy() {
        compositeDisposable.clear()
    }

}