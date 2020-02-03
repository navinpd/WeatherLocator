package com.navin.downloadfiletest.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
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
    private lateinit var noListText: TextView
    private lateinit var localCityText: TextView

    private lateinit var progressCircle: ProgressBar
    private lateinit var searchViewAdapter: SearchViewAdapter

    private lateinit var selectableViewAdapter: SelectableViewAdapter
    private var cntr: CountDownTimer? = null
    private var listOfSearchCity = mutableListOf<String>()
    private var listOfSelectableCity = mutableListOf<String>()

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
            progressCircle.visibility = View.VISIBLE
            Toast.makeText(this.context, "You Clicked: $selectedCity", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).launchCityDetails(selectedCity)
            searchView.setQuery("", true)
        }


    private val selectItemClickListener =
        View.OnClickListener { view ->
            val selectedCity = view.tag as String
            Toast.makeText(this.context, "Go for details of: $selectedCity", Toast.LENGTH_SHORT)
                .show()
            (activity as MainActivity).launchCityDetails(selectedCity)
            searchView.setQuery("", true)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)

        cityQueryViewModel.getSearchResults.observe(viewLifecycleOwner, Observer {

            if (it?.search_api != null && !it.search_api.result.isNullOrEmpty()) {
                //Clear List items
                listOfSearchCity = mutableListOf()

                it.search_api.result.forEach { result ->
                    Log.d(TAG, result.areaName[0].value)
                    val cityName = "${result.areaName[0].value}-${result.country[0].value} "
                    listOfSearchCity.add(cityName)
                }

                progressCircle.visibility = View.GONE
                searchViewAdapter.updateCityList(listOfSearchCity)

            } else {

                progressCircle.visibility = View.GONE
                searchViewAdapter.updateCityList(mutableListOf())
            }

            Log.d(TAG, "Got City list $listOfSearchCity")
        })

        cityQueryViewModel.getLocalSavedData.observe(viewLifecycleOwner, Observer {

            if (it.isEmpty()) {

                localCityText.visibility = View.GONE
                noListText.visibility = View.VISIBLE
            } else {

                selectableViewAdapter.updateCityList(it)
                noListText.visibility = View.GONE
                localCityText.visibility = View.VISIBLE
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(var1: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(searchTerm: String?): Boolean {
                cntr?.let { cntr?.cancel() }

                //Delay of 500mS to wait for next text
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

    override fun onActivityCreated(savedInstanceState: Bundle?) { // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        cityQueryViewModel.updateLocallySavedList()
    }

    private fun initView(view: View) {
        searchRecyclerView = view.findViewById(R.id.search_view_rv)
        selectableRecyclerView = view.findViewById(R.id.selectable_city_rv)
        searchView = view.findViewById(R.id.search_sv)
        noListText = view.findViewById(R.id.no_result_text)
        progressCircle = view.findViewById(R.id.progress_circle)
        localCityText = view.findViewById(R.id.local_data_header_tv)

        searchViewAdapter = SearchViewAdapter(listOfSearchCity)
        searchRecyclerView.layoutManager = LinearLayoutManager(this.context)
        searchRecyclerView.adapter = searchViewAdapter
        searchViewAdapter.setOnItemClickListener(searchItemClickListener)

        selectableViewAdapter = SelectableViewAdapter(listOfSelectableCity)
        selectableRecyclerView.layoutManager = LinearLayoutManager(this.context)
        selectableRecyclerView.adapter = selectableViewAdapter
        selectableViewAdapter.setOnItemClickListener(selectItemClickListener)

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