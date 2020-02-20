package com.navin.downloadfiletest.di.module

import androidx.lifecycle.LifecycleRegistry
import com.navin.downloadfiletest.di.ViewHolderScope
import com.navin.downloadfiletest.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewHolderScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)


}