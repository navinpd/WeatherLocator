package com.navin.downloadfiletest.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.navin.downloadfiletest.MyApplication
import com.navin.downloadfiletest.di.component.FragmentComponent
import com.navin.downloadfiletest.di.module.ViewHolderModule
import javax.inject.Inject

abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
    @LayoutRes res: Int,
    val parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(res, parent, false)),
    LifecycleOwner {

    init {
        onCreate()
    }

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    open fun bind(data: T) {
        viewModel.updateData(data)
    }

    open fun onCreate() {
        injectDependencies(buildViewHolderComponent())
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        setUpObservers()
        setUpViews(itemView)
    }

    open fun onStart() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    open fun onStop() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    open fun onDestroy() {

        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }


    fun buildViewHolderComponent() =
        DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context.applicationContext as MyApplication).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()


    fun showMessage(message: String) =
        parent.let { Toast.makeText(parent.context, message, Toast.LENGTH_SHORT).show() }

    protected open fun setUpObservers() {
        viewModel.messageString.observe(this, Observer {
            showMessage(it)
        })

        viewModel.messageStringId.observe(this, Observer {
            showMessage(parent.context.getString(it))
        })
    }


    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setUpViews(view: View)

}