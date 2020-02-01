package com.navin.downloadfiletest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.DaggerFragmentComponent
import com.navin.downloadfiletest.di.module.DetailsFragmentModule
import javax.inject.Inject

class CityDetailsFragment : Fragment() {

    @Inject
    lateinit var cityDetailsViewModel: CityDetailsViewModel

    companion object {
        const val TAG = "CityDetailsFragment"
        const val CITY_NAME = "CityDetailsFragment.CITY_NAME"

        fun newInstance(city: String): CityDetailsFragment {
            val cityDetailsFragment = CityDetailsFragment()
            val bundle = Bundle()
            bundle.putString(CITY_NAME, city)
            cityDetailsFragment.arguments = bundle
            return cityDetailsFragment
        }
    }

    lateinit var humidityText: TextView
    lateinit var weatherText: TextView
    lateinit var weatherImage: ImageView
    lateinit var temperatureText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_city_details, container, false)

        humidityText = rootView.findViewById(R.id.humidity_tv)
        weatherText = rootView.findViewById(R.id.weather_tv)
        temperatureText = rootView.findViewById(R.id.temperature_tv)
        weatherImage = rootView.findViewById(R.id.weather_iv)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (arguments != null) {
            val city: String? = arguments!!.getString(CITY_NAME)
            if (!city.isNullOrEmpty()) {
                Log.d(TAG, city)
                queryCityWeather(city)
            }
        }

        cityDetailsViewModel.getCityDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())

            temperatureText.text =
                getString(R.string.in_degree_c, it.current_condition[0].temp_C.toString())

            humidityText.text = getString(
                R.string.humidity, it.current_condition[0].humidity.toString()
            )

            weatherText.text = it.weather[0].toString()
            weatherImage.load(
                it.current_condition[0].weatherIconUrl[0].value
            ) {
                placeholder(R.drawable.weather_icon)
            }
        })
    }


    fun queryCityWeather(query: String) {
        cityDetailsViewModel.queryCityDetails(query)
    }

    // to suppress null pointer exception warning
    private fun getDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent(
                (context!!
                    .applicationContext as MyApplication).applicationComponent
            )
            .detailsFragmentModule(DetailsFragmentModule(this)) // this is shown as deprecated as no instance provided by it is being injected
            .build()
            .inject(this)
    }

}