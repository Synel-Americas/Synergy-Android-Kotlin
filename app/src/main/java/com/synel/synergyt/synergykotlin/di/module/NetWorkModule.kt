package com.synel.synergyt.synergykotlin.di.module

import com.google.gson.GsonBuilder
import com.synel.synergyt.synergykotlin.model.webservice.SynergyRetrofitClient
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return SynergyRetrofitClient.getClient()
    }

}