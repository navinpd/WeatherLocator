package com.navin.downloadfiletest.di.module

import android.content.Context
import com.navin.downloadfiletest.di.ActivityContext
import com.navin.downloadfiletest.ui.base.BaseFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    @ActivityContext
    fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()

    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.context!!
}
