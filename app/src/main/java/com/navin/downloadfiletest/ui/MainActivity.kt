package com.navin.downloadfiletest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.DaggerActivityComponent
import com.navin.downloadfiletest.di.module.ActivityModule
import com.navin.downloadfiletest.ui.fragment.CityDetailsFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment

class MainActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val fragmentTransaction = fragmentManager.beginTransaction()

    companion object {
        const val TAG = "MainViewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = CityQueryFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    fun launchCityQuery(city : String) {
        val cityDetailsFragment = CityDetailsFragment.newInstance(city)
        fragmentTransaction.replace(R.id.fragment_container, cityDetailsFragment)
        fragmentTransaction.commit()
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
