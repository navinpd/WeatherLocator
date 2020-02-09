package com.navin.downloadfiletest.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.di.component.ActivityComponent
import com.navin.downloadfiletest.di.component.DaggerActivityComponent
import com.navin.downloadfiletest.di.module.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        buildActivityComponent()
        setUpView(savedInstanceState)
        setContentView(provideLayoutId())
    }

    fun goBack() = onBackPressed()


    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setUpView(savedInstanceState: Bundle?)

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)


    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    open fun setUpObserver() {
        viewModel.messageString.observe(this, Observer {
            showMessage(it)
        })
        viewModel.messageStringId.observe(this, Observer {
            showMessage(getString(it))
        })

    }

    fun showMessage(string: String) = Toast.makeText(this, string, Toast.LENGTH_SHORT).show()

}