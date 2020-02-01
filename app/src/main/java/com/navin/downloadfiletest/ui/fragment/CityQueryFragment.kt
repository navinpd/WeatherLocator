package com.navin.downloadfiletest.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.DaggerFragmentComponent
import com.navin.downloadfiletest.di.module.FragmentModule
import com.navin.downloadfiletest.ui.MainActivity
import javax.inject.Inject


class CityQueryFragment : BaseFragment() {

    @Inject
    lateinit var cityQueryViewModel: CityQueryViewModel

    private lateinit var searchListView: View
    private lateinit var searchView: SearchView
    private lateinit var selectableListView: View

    private var queryString: String = ""
    private val handler: Handler = Handler()
    private var listOfCity: List<String> = listOf()

    companion object {
        const val TAG = "CityQueryFragment"
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
        return inflater.inflate(R.layout.fragment_query_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchListView = view.findViewById<View>(R.id.selectable_city_rv)
        selectableListView = view.findViewById<View>(R.id.search_view_rv)
        searchView = view.findViewById(R.id.search_sv)

        cityQueryViewModel.getSearchResults.observe(viewLifecycleOwner, Observer {
            if (!it.result.isNullOrEmpty()) {
                //Clear List items
                listOfCity = listOf()

                val size: Int = it.result.size
                while (size > 0) {
                    // Put list items in list
                    Log.d(TAG, it.result.get(size - 1).areaName.__cdata)
                    listOfCity.toMutableList().add(it.result.get(size - 1).areaName.__cdata)
                }

                // for test delay 2S and query for city weather
                handler.postDelayed({

                    getCityDetail(listOfCity[0])

                }, 2000)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(var1: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchTerm: String?): Boolean {
                queryString = searchTerm!!
                handler.removeCallbacksAndMessages(null)

                handler.postDelayed(Runnable {
                    //Put your call to the server here (with mQueryString)

                    if (!queryString.isNullOrEmpty())
                        cityQueryViewModel.getSearchResult(queryString!!)
                }, 400)
                return true
            }

        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun getCityDetail(cityName: String) {
        (activity as MainActivity).launchCityQuery(cityName)
    }


    override fun onDestroy() {
        super.onDestroy()
        cityQueryViewModel.onDestroy()
    }


    private// to suppress null pointer exception warning
    fun getDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent(
                (context!!
                    .applicationContext as MyApplication).applicationComponent
            )
            .fragmentModule(FragmentModule(this)) // this is shown as deprecated as no instance provided by it is being injected
            .build()
            .inject(this)
    }

}