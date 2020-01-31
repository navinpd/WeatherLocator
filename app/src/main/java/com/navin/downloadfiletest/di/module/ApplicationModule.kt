package com.navin.downloadfiletest.di.module

import android.content.Context
import com.navin.downloadfiletest.BuildConfig
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.data.remote.Networking
import com.navin.downloadfiletest.di.ApplicationContext
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ApplicationModule(private val application: MyApplication) {


    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun provideCompositDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideNetworking(): NetworkService {
        return Networking.createNetworking(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024
        )
    }

}