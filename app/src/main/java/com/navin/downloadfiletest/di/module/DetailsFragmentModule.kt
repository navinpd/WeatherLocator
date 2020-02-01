package com.navin.downloadfiletest.di.module

import android.content.Context
import com.navin.downloadfiletest.di.ActivityContext
import com.navin.downloadfiletest.ui.fragment.CityDetailsFragment
import dagger.Module
import dagger.Provides

@Module
class DetailsFragmentModule(private val fragment: CityDetailsFragment) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.context!!
}
