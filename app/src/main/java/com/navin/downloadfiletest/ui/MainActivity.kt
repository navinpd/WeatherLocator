package com.navin.downloadfiletest.ui

import android.os.Bundle
import android.util.Log
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.ActivityComponent
import com.navin.downloadfiletest.ui.base.BaseActivity
import com.navin.downloadfiletest.ui.fragment.CityDetailsFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment

class MainActivity : BaseActivity<MainViewModel>() {

    private val fragmentManager = supportFragmentManager

    val TAG = this.javaClass.simpleName


    fun launchCityDetails(city: String) {
        Log.d(TAG, "Launch CityDetailsFragment")

        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CityDetailsFragment.newInstance(city))
            .addToBackStack(CityDetailsFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }

    fun launchCityQuery() {
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, CityQueryFragment())
            .addToBackStack(CityDetailsFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun setUpView(savedInstanceState: Bundle?) {
        launchCityQuery()
    }

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)
}
