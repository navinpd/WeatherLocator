package com.navin.downloadfiletest.di.module

import android.content.Context
import com.navin.downloadfiletest.di.ActivityContext
import com.navin.downloadfiletest.ui.MainActivity
import com.navin.downloadfiletest.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
public class ActivityModule(private val mainActivity: BaseActivity<*>) {


    @Provides
    @ActivityContext
    fun provideContext(): Context = mainActivity


}