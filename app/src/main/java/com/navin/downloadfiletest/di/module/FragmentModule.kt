package com.navin.downloadfiletest.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.navin.downloadfiletest.di.ActivityContext
import com.navin.downloadfiletest.ui.fragment.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: BaseFragment) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.context!!
}
