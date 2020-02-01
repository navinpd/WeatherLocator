package com.navin.downloadfiletest.ui

import android.os.Bundle
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
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = CityQueryFragment()
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
            .addToBackStack("CITY_QUERY")
            .commitAllowingStateLoss()
    }

    fun launchCityQuery(city : String) {
        val cityDetailsFragment = CityDetailsFragment.newInstance(city)
        Log.d(TAG,"Launch CityDetailsFragment")
        fragmentManager.beginTransaction().replace(R.id.fragment_container, cityDetailsFragment)
            .addToBackStack("CITY_DETAIL")
            .commitAllowingStateLoss()
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}
