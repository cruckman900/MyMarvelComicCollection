package com.cruckman900.mymarvelcomiccollection.di.component

import com.cruckman900.mymarvelcomiccollection.di.module.MMCCApplicationModule
import com.cruckman900.mymarvelcomiccollection.di.module.MMCCRepositoryModule
import com.cruckman900.mymarvelcomiccollection.di.module.MMCCViewModelModule
import com.cruckman900.mymarvelcomiccollection.di.module.NetworkModule
import com.cruckman900.mymarvelcomiccollection.view.CollectionFragment
import com.cruckman900.mymarvelcomiccollection.view.NewReleasesFragment
import com.cruckman900.mymarvelcomiccollection.view.WishlistFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class,
    MMCCApplicationModule::class,
    MMCCRepositoryModule::class,
    MMCCViewModelModule::class
])
@Singleton
interface MMCCComponent {
    fun injectNewReleases(fragment: NewReleasesFragment)
    fun injectCollection(fragment: CollectionFragment)
    fun injectWishlist(fragment: WishlistFragment)
}