package com.navin.downloadfiletest.di.module

import android.content.Context
import com.navin.downloadfiletest.di.ActivityContext
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment
import dagger.Module
import dagger.Provides

@Module
class QueryFragmentModule(private val fragment: CityQueryFragment) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.context!!
}
