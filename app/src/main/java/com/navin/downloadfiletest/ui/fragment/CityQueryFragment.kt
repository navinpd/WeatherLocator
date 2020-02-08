package com.navin.downloadfiletest.ui.fragment

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.di.component.FragmentComponent
import com.navin.downloadfiletest.ui.MainActivity
import com.navin.downloadfiletest.ui.adapter.SearchViewAdapter
import com.navin.downloadfiletest.ui.adapter.SelectableViewAdapter
import com.navin.downloadfiletest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_query_city.*
import javax.inject.Inject

/**
 * This is launcher fragment from MainActivity. Supporting the user action
 * to search for city.  Also this is going to save last 10 city names in recent 10 first order.
 *
 */
class CityQueryFragment : BaseFragment<CityQueryViewModel>() {

    @Inject
    lateinit var cityQueryViewModel: CityQueryViewModel

    private lateinit var searchViewAdapter: SearchViewAdapter

    private lateinit var selectableViewAdapter: SelectableViewAdapter

    private var timer: CountDownTimer? = null

    private var listOfSearchCity = mutableListOf<String>()
    private var listOfSelectableCity = mutableListOf<String>()

    val TAG = this.javaClass.simpleName

    private val searchItemClickListener =
        View.OnClickListener { view ->
            val selectedCity = view.tag as String

            progress_circle.visibility = View.VISIBLE

            Toast.makeText(
                this.context,
                getString(R.string.you_clicked, selectedCity),
                Toast.LENGTH_SHORT
            ).show()

            (activity as MainActivity).launchCityDetails(selectedCity)

            search_sv.setQuery("", true)
        }


    private val selectItemClickListener =
        View.OnClickListener { view ->
            val selectedCity = view.tag as String

            Toast.makeText(
                this.context,
                getString(R.string.go_for_city, selectedCity),
                Toast.LENGTH_SHORT
            ).show()

            (activity as MainActivity).launchCityDetails(selectedCity)

            search_sv.setQuery("", true)
        }

    override fun onResume() {
        super.onResume()
        cityQueryViewModel.updateLocallySavedList()
    }

    override fun setUpViews(view: View) {
        searchViewAdapter = SearchViewAdapter(listOfSearchCity)
        search_view_rv.layoutManager = LinearLayoutManager(this.context)
        search_view_rv.adapter = searchViewAdapter
        searchViewAdapter.setOnItemClickListener(searchItemClickListener)

        selectableViewAdapter = SelectableViewAdapter(listOfSelectableCity)
        selectable_city_rv.layoutManager = LinearLayoutManager(this.context)
        selectable_city_rv.adapter = selectableViewAdapter
        selectableViewAdapter.setOnItemClickListener(selectItemClickListener)

        cityQueryViewModel.getSearchResults.observe(viewLifecycleOwner, Observer {

            if (it?.search_api != null && !it.search_api.result.isNullOrEmpty()) {
                //Clear List items
                listOfSearchCity = mutableListOf()

                it.search_api.result.forEach { result ->
                    Log.d(TAG, result.areaName[0].value)
                    val cityName = "${result.areaName[0].value}-${result.country[0].value} "
                    listOfSearchCity.add(cityName)
                }

                progress_circle.visibility = View.GONE
                searchViewAdapter.updateCityList(listOfSearchCity)

            } else {

                progress_circle.visibility = View.GONE
                searchViewAdapter.updateCityList(mutableListOf())
            }

            Log.d(TAG, "Got City list $listOfSearchCity")
        })

        cityQueryViewModel.getLocalSavedData.observe(viewLifecycleOwner, Observer {

            if (it.isEmpty()) {
                local_data_header_tv.visibility = View.GONE
                no_result_text.visibility = View.VISIBLE

            } else {
                selectableViewAdapter.updateCityList(it)
                no_result_text.visibility = View.GONE
                local_data_header_tv.visibility = View.VISIBLE

            }
        })

        search_sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(var1: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchTerm: String?): Boolean {
                timer?.let { timer?.cancel() }

                //Delay of 500mS to wait for next text
                timer = object : CountDownTimer(500, 500) {
                    override fun onTick(millisUntilFinished: Long) {
                    }

                    override fun onFinish() {
                        cityQueryViewModel.getSearchResult(searchTerm!!)
                        timer?.cancel()
                    }
                }

                timer?.start()
                return true
            }

        })

    }

    override fun getLayoutReference(): Int = R.layout.fragment_query_city

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

}