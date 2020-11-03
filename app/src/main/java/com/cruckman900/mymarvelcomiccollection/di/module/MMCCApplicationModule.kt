package com.cruckman900.mymarvelcomiccollection.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class MMCCApplicationModule(val context: Context) {
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }
}