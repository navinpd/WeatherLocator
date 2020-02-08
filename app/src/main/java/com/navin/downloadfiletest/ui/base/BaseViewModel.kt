package com.navin.downloadfiletest.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navin.downloadfiletest.data.remote.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    val networkHelper: NetworkHelper,
    val compositeDisposable: CompositeDisposable
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageString = MutableLiveData<String>()

    val messageStringId = MutableLiveData<Int>()

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            // handle the error
        }

    abstract fun onCreate()
}