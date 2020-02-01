package com.navin.downloadfiletest.di.component

import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.di.module.FragmentModule
import com.navin.downloadfiletest.ui.fragment.BaseFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment

import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: BaseFragment)
}
