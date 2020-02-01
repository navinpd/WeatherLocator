package com.navin.downloadfiletest.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.DaggerFragmentComponent
import com.navin.downloadfiletest.di.module.QueryFragmentModule
import com.navin.downloadfiletest.ui.MainActivity
import javax.inject.Inject


class CityQueryFragment : Fragment() {

    @Inject
    lateinit var cityQueryViewModel: CityQueryViewModel

    private lateinit var searchListView: View
    private lateinit var searchView: SearchView
    private lateinit var selectableListView: View

    private var listOfCity = mutableListOf<String>()
    private var cntr : CountDownTimer? = null

    companion object {
        const val TAG = "CityQueryFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
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
            if (it != null && it.search_api != null && !it.search_api.result.isNullOrEmpty()) {
                //Clear List items
                listOfCity = mutableListOf()

                var size: Int = it.search_api.result.size
                while (size > 0) {
                    // Put result items in listOfCity
                    Log.d(TAG, it.search_api.result.get(size - 1).areaName[0].value)

                    listOfCity.add(it.search_api.result.get(size - 1).areaName[0].value)

                    size -= 1
                }
                Log.d(TAG, "Got City list $listOfCity")

                //TODO: Do this onclick of adapter
                (activity as MainActivity).launchCityQuery((listOfCity[0]))
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(var1: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchTerm: String?): Boolean {
                if (cntr != null) {
                    cntr?.cancel()
                }
                cntr = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        cityQueryViewModel.getSearchResult(searchTerm!!)
                        cntr?.cancel()
                    }
                }
                cntr?.start()
                return true
            }

        })

        super.onViewCreated(view, savedInstanceState)
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
            .queryFragmentModule(QueryFragmentModule(this)) // this is shown as deprecated as no instance provided by it is being injected
            .build()
            .inject(this)
    }

}