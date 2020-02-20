package com.navin.downloadfiletest.di.component

import androidx.lifecycle.LifecycleRegistry
import com.navin.downloadfiletest.di.ViewHolderScope
import com.navin.downloadfiletest.di.module.ApplicationModule
import com.navin.downloadfiletest.di.module.ViewHolderModule
import dagger.Component


@Component(
    dependencies = [ApplicationModule::class],
    modules = [ViewHolderModule::class]
)
@ViewHolderScope
interface ViewHolderComponent {

    //TODO you need to use respective ViewHolders here
//    fun inject(viewHolder: PostViewHolder)
}
