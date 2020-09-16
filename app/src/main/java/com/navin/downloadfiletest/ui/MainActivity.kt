package com.navin.downloadfiletest.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.DaggerActivityComponent
import com.navin.downloadfiletest.di.module.ActivityModule
import com.navin.downloadfiletest.ui.fragment.CityDetailsFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, CityQueryFragment())
            .addToBackStack(CityQueryFragment.TAG)
            .commitAllowingStateLoss()

        Handler().postDelayed(Runnable {  }, 100);
    }

    fun launchCityDetails(city: String) {
        Log.d(TAG, "Launch CityDetailsFragment")

        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CityDetailsFragment.newInstance(city))
            .addToBackStack(CityDetailsFragment.TAG)
            .commitAllowingStateLoss()
    }

    private fun setUpDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}
