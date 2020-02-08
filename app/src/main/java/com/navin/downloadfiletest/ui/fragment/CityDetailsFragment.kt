package com.navin.downloadfiletest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.FragmentComponent
import com.navin.downloadfiletest.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_city_details.*
import javax.inject.Inject


/**
 * This fragment is for user to get City Details from network and get full city weather details.
 * City list will save last visited city in local for users to make quick access to that.
 */
class CityDetailsFragment : BaseFragment<CityDetailsViewModel>() {

    @Inject
    lateinit var cityDetailsViewModel: CityDetailsViewModel

    @Inject
    lateinit var picasso: Picasso

    val TAG = this.javaClass.simpleName

    companion object {
        const val CITY_NAME = "CityDetailsFragment.CITY_NAME"

        fun newInstance(city: String): CityDetailsFragment {
            val cityDetailsFragment = CityDetailsFragment()
            val bundle = Bundle()
            bundle.putString(CITY_NAME, city)
            cityDetailsFragment.arguments = bundle
            return cityDetailsFragment
        }
    }

    override fun setUpViews(view: View) {
        if (arguments != null) {
            val city: String? = arguments!!.getString(CITY_NAME)
            if (!city.isNullOrEmpty()) {
                Log.d(TAG, city)

                //Network query for City
                cityDetailsViewModel.queryCityDetails(city)
                progress_bar.visibility = View.VISIBLE
            }
        }

        cityDetailsViewModel.getCityDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())

            progress_bar.visibility = View.GONE
            weather_details_cv.visibility = View.VISIBLE
            network_error.visibility = View.GONE

            val currentCondition = it.current_condition[0]

            temperature_tv.text =
                getString(R.string.in_degree_c, currentCondition.temp_C.toString())

            humidity_tv.text = getString(
                R.string.humidity, currentCondition.humidity.toString()
            )

            weather_tv.text = getString(
                R.string.weather_text, it.weather[0].astronomy[0].sunrise,
                it.weather[0].astronomy[0].sunset,
                it.weather[0].maxtempC,
                it.weather[0].mintempC
            )

            city_tv.text = it.request[0].query

            picasso.load(it.current_condition[0].weatherIconUrl[0].value)
                .error((R.drawable.weather_icon))
                .into(weather_iv)

            cityDetailsViewModel.saveToLocal(arguments!!.getString(CITY_NAME)!!)
        })

        cityDetailsViewModel.noInternetLiveData.observe(viewLifecycleOwner, Observer {
            weather_details_cv.visibility = View.GONE
            network_error.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE

            if (it.contains("UnknownHostException")) {
                network_error.text = getString(R.string.no_internet)

            } else {
                network_error.text = it

            }
        })
    }

    override fun getLayoutReference(): Int = R.layout.fragment_city_details

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

}