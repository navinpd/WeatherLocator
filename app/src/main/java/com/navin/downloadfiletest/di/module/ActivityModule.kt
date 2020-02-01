package com.navin.downloadfiletest.di.module

import android.content.Context
import com.navin.downloadfiletest.di.ActivityContext
import com.navin.downloadfiletest.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
public class ActivityModule(private val mainActivity: MainActivity) {


    @Provides
    @ActivityContext
    fun provideContext(): Context = mainActivity


}