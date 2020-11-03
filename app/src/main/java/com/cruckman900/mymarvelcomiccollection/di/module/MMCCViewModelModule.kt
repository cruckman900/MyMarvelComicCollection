package com.cruckman900.mymarvelcomiccollection.di.module

import com.cruckman900.mymarvelcomiccollection.model.MMCCRepository
import com.cruckman900.mymarvelcomiccollection.viewmodel.MMCCViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class MMCCViewModelModule {
    @Provides
    fun provideViewModel(repo: MMCCRepository): MMCCViewModelProvider {
        return MMCCViewModelProvider(repo)
    }
}