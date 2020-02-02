package com.navin.downloadfiletest.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.DaggerFragmentComponent
import com.navin.downloadfiletest.di.module.DetailsFragmentModule
import com.navin.downloadfiletest.utils.CardList
import com.navin.downloadfiletest.utils.LOCAL_LIST
import com.navin.downloadfiletest.utils.LocalCityArray
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CityDetailsFragment : Fragment() {

    @Inject
    lateinit var cityDetailsViewModel: CityDetailsViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var humidityText: TextView
    private lateinit var weatherText: TextView
    private lateinit var weatherImage: ImageView
    private lateinit var temperatureText: TextView
    private lateinit var cityText: TextView

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
        initView(rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (arguments != null) {
            val city: String? = arguments!!.getString(CITY_NAME)
            if (!city.isNullOrEmpty()) {
                Log.d(TAG, city)
                cityDetailsViewModel.queryCityDetails(city)
            }
        }

        cityDetailsViewModel.getCityDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())
            val currentCondition = it.current_condition[0]

            temperatureText.text =
                getString(R.string.in_degree_c, currentCondition.temp_C.toString())

            humidityText.text = getString(
                R.string.humidity, currentCondition.humidity.toString()
            )

            weatherText.text = getString(
                R.string.weather_text, it.weather[0].astronomy[0].sunrise,
                it.weather[0].astronomy[0].sunset,
                it.weather[0].maxtempC,
                it.weather[0].mintempC
            )

            cityText.text = it.request[0].query

            Picasso.get()
                .load(it.current_condition[0].weatherIconUrl[0].value)
                .error((R.drawable.weather_icon))
                .into(weatherImage)
            saveToLocal(arguments!!.getString(CITY_NAME)!!)
        })
    }

    private fun saveToLocal(city: String) {
        val currentCityData = CardList(city, System.currentTimeMillis())

        var savedCityList = LocalCityArray(mutableListOf())
        var finalCityData = ""

        if (sharedPreferences.getString(LOCAL_LIST, "")!!.isNotEmpty()) {
            savedCityList = Gson()
                .fromJson<LocalCityArray>(
                    sharedPreferences.getString(LOCAL_LIST, ""),
                    LocalCityArray::class.java
                )
        }

        var cityIfPresent: CardList? = null

        when (savedCityList.cardList.size) {

            0 -> {
                finalCityData = "{\"CardList\":[${currentCityData}]}"

            }
            else -> {
                savedCityList.cardList.forEach {
                    if (it.cityName == city) {
                        cityIfPresent = it
                    }
                }
                cityIfPresent?.let {
                    savedCityList.cardList.remove(cityIfPresent!!)
                }

                savedCityList.cardList.add(0, currentCityData)
                finalCityData = savedCityList.toString()
            }
        }

        if (savedCityList.cardList.size > 10) {
            savedCityList.cardList.removeAt(10)
            finalCityData = savedCityList.toString()
        }
        Log.d(TAG, "City Details is $finalCityData")

        sharedPreferences.edit().putString(LOCAL_LIST, finalCityData).apply()
    }

    private fun initView(rootView: View) {
        humidityText = rootView.findViewById(R.id.humidity_tv)
        weatherText = rootView.findViewById(R.id.weather_tv)
        temperatureText = rootView.findViewById(R.id.temperature_tv)
        weatherImage = rootView.findViewById(R.id.weather_iv)
        cityText = rootView.findViewById(R.id.city_tv)

    }

    private fun getDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent(
                (context!!
                    .applicationContext as MyApplication).applicationComponent
            )
            .detailsFragmentModule(DetailsFragmentModule(this))
            .build()
            .inject(this)
    }

}