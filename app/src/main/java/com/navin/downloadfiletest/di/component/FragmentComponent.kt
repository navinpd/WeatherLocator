package com.navin.downloadfiletest.di.component

import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.di.module.DetailsFragmentModule
import com.navin.downloadfiletest.di.module.QueryFragmentModule
import com.navin.downloadfiletest.ui.fragment.CityDetailsFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [QueryFragmentModule::class, DetailsFragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: CityQueryFragment)

    fun inject(fragment: CityDetailsFragment)
}
