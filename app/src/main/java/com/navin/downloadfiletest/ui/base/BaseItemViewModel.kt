package com.navin.downloadfiletest.ui.base

import androidx.lifecycle.MutableLiveData
import com.navin.downloadfiletest.data.remote.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseItemViewModel<T : Any>
@Inject constructor(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(networkHelper, compositeDisposable) {

    val data: MutableLiveData<T> = MutableLiveData()

    abstract fun onManualClear()

    fun updateData(data: T) {
        this.data.postValue(data)
    }

}