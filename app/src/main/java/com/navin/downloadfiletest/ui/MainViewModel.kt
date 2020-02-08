package com.navin.downloadfiletest.ui

import androidx.lifecycle.MutableLiveData
import com.navin.downloadfiletest.data.remote.NetworkHelper
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val networkService: NetworkService
) : BaseViewModel(networkHelper, compositeDisposable) {

    val testData = MutableLiveData<String>()

    override fun onCreate() {
        testData.postValue("Hello from MainViewModel")
    }

}