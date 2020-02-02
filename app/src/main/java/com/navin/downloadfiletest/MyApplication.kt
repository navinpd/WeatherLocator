package com.navin.downloadfiletest

import android.app.Application
import android.content.SharedPreferences
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.di.component.ApplicationComponent
import com.navin.downloadfiletest.di.component.DaggerApplicationComponent
import com.navin.downloadfiletest.di.module.ApplicationModule
import javax.inject.Inject

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }


    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}