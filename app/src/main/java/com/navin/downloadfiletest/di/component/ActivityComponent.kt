package com.navin.downloadfiletest.di.component

import com.navin.downloadfiletest.data.remote.NetworkService
import com.navin.downloadfiletest.di.ActivityScope
import com.navin.downloadfiletest.di.module.ActivityModule
import com.navin.downloadfiletest.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)


}