package com.navin.downloadfiletest.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.data.remote.response.search_response.Result
import com.navin.downloadfiletest.di.component.DaggerFragmentComponent
import com.navin.downloadfiletest.di.module.QueryFragmentModule
import com.navin.downloadfiletest.ui.MainActivity
import com.navin.downloadfiletest.ui.adapter.SearchViewAdapter
import com.navin.downloadfiletest.ui.adapter.SelectableViewAdapter
import javax.inject.Inject


class CityQueryFragment : Fragment() {

    @Inject
    lateinit var cityQueryViewModel: CityQueryViewModel

    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var selectableRecyclerView: RecyclerView
    private lateinit var searchView: SearchView

    private var cntr: CountDownTimer? = null
    private var listOfCity = mutableListOf<String>()
    private lateinit var searchViewAdapter: SearchViewAdapter
    private lateinit var selectableViewAdapter: SelectableViewAdapter

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

    private val searchItemClickListener =
        View.OnClickListener { view ->
            val selectedCity = view.tag as String
            Toast.makeText(this.context, "You Clicked: $selectedCity", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).launchCityDetails(selectedCity)
            searchView.setQuery("", true)
        }


    private val detailItemClickListener =
        View.OnClickListener { view ->
            val selectedCity = view.tag as String
            Toast.makeText(this.context, "Go for details of: $selectedCity", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).launchCityDetails(selectedCity)
            searchView.setQuery("", true)
        }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)

        cityQueryViewModel.getSearchResults.observe(viewLifecycleOwner, Observer {
            if (it != null && it.search_api != null && !it.search_api.result.isNullOrEmpty()) {
                //Clear List items
                listOfCity = mutableListOf()

                var size: Int = it.search_api.result.size
                while (size > 0) {
                    // Put result items in listOfCity
                    val result: Result = it.search_api.result.get(size - 1)
                    Log.d(TAG, result.areaName[0].value)
                    val cityName = "${result.areaName[0].value}-${result.country[0].value} "
                    listOfCity.add(cityName)
                    size -= 1
                }

                searchViewAdapter.updateCityList(listOfCity)

            } else {
                searchViewAdapter.updateCityList(mutableListOf())
            }
            Log.d(TAG, "Got City list $listOfCity")
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

    private fun initView(view: View) {
        searchRecyclerView = view.findViewById(R.id.selectable_city_rv)
        selectableRecyclerView = view.findViewById(R.id.search_view_rv)
        searchView = view.findViewById(R.id.search_sv)
        searchViewAdapter = SearchViewAdapter(listOfCity)
        selectableViewAdapter = SelectableViewAdapter(listOfCity)

        searchRecyclerView.layoutManager = LinearLayoutManager(this.context)
        searchRecyclerView.adapter = searchViewAdapter
        searchViewAdapter.setOnItemClickListener(searchItemClickListener);

        selectableRecyclerView.layoutManager = LinearLayoutManager(this.context)
        selectableRecyclerView.adapter = selectableViewAdapter
        selectableViewAdapter.setOnItemClickListener(detailItemClickListener);

    }

    override fun onDestroy() {
        super.onDestroy()
        cityQueryViewModel.onDestroy()
    }


    private fun getDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent(
                (context!!
                    .applicationContext as MyApplication).applicationComponent
            )
            .queryFragmentModule(QueryFragmentModule(this))
            .build()
            .inject(this)
    }

}