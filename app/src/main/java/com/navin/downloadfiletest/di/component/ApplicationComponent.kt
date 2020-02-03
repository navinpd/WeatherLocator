package com.navin.downloadfiletest.di.component

import android.content.Context
import android.content.SharedPreferences
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.di.ApplicationContext
import com.navin.downloadfiletest.di.module.ApplicationModule
import com.squareup.picasso.Picasso
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    @ApplicationContext
    fun getContext() : Context

    fun getCompositeDisposable(): CompositeDisposable

    fun getNetworkService() : NetworkService

    fun getPrefStore() : SharedPreferences

    fun getPicasso() : Picasso

}