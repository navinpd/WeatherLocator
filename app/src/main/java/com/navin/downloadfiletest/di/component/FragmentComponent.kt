package com.navin.downloadfiletest.di.component

import com.navin.downloadfiletest.di.FragmentScope
import com.navin.downloadfiletest.di.module.FragmentModule
import com.navin.downloadfiletest.ui.fragment.CityDetailsFragment
import com.navin.downloadfiletest.ui.fragment.CityQueryFragment
import dagger.Component
import io.reactivex.disposables.CompositeDisposable

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: CityQueryFragment)

    fun inject(fragment: CityDetailsFragment)

    fun inject(compositeDisposable: CompositeDisposable)
}
