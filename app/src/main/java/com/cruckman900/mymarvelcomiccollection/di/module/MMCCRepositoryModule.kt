package com.cruckman900.mymarvelcomiccollection.di.module

import com.cruckman900.mymarvelcomiccollection.model.MMCCRepository
import com.cruckman900.mymarvelcomiccollection.model.MMCCApi
import dagger.Module
import dagger.Provides

@Module
class MMCCRepositoryModule {
    @Provides
    fun provideMMCCRepository(api: MMCCApi): MMCCRepository {
        return MMCCRepository(api)
    }
}