package com.navin.downloadfiletest.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.R
import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.di.component.DaggerActivityComponent
import com.navin.downloadfiletest.di.module.ActivityModule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var mainViewModel: MainViewModel

    companion object {
        const val TAG = "MainViewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = MainViewModel(compositeDisposable, networkService)


        findViewById<Button>(R.id.search_bt).setOnClickListener {
            mainViewModel.getSearchResult(findViewById<EditText>(R.id.search_et).text.toString())
        }

        mainViewModel.getSearchResults.observe(this, Observer {
            Log.d(TAG, it.toString())
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.onDestroy()
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
