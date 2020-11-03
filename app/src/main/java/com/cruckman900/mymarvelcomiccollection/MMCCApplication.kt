package com.cruckman900.mymarvelcomiccollection

import android.app.Application
import com.cruckman900.mymarvelcomiccollection.di.component.DaggerMMCCComponent
import com.cruckman900.mymarvelcomiccollection.di.component.MMCCComponent
import com.cruckman900.mymarvelcomiccollection.di.module.MMCCApplicationModule
import com.cruckman900.mymarvelcomiccollection.di.module.MMCCRepositoryModule
import com.cruckman900.mymarvelcomiccollection.di.module.MMCCViewModelModule
import com.cruckman900.mymarvelcomiccollection.di.module.NetworkModule

class MMCCApplication: Application() {
    companion object {
        private lateinit var daggerComponent: MMCCComponent

        fun getComponent() = daggerComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerMMCCComponent.builder()
            .networkModule((NetworkModule()))
            .mMCCRepositoryModule(MMCCRepositoryModule())
            .mMCCViewModelModule(MMCCViewModelModule())
            .mMCCApplicationModule(MMCCApplicationModule(this))
            .build()
    }
}