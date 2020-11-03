package com.cruckman900.mymarvelcomiccollection.di.module

import com.cruckman900.mymarvelcomiccollection.model.MMCCApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideMMCCApi(moshiConverterFactory: MoshiConverterFactory): MMCCApi {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443")
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(MMCCApi::class.java)
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }
}