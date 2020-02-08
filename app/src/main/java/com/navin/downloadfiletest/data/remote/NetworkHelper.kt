package com.navin.downloadfiletest.data.remote

import android.content.Context
import android.net.ConnectivityManager
import com.navin.downloadfiletest.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(
    // Should be Application Context
    @ApplicationContext private val context: Context
) {

    // will check for network connectivity
    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }

}
