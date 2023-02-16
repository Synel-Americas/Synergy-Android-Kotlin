package com.synel.synergyt.synergykotlin.di.module

import com.synel.synergyt.synergykotlin.model.webservice.SynergyRetrofitClient
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceAPI
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    fun provideWebServiceApi(retrofit: Retrofit): WebServiceAPI =
        SynergyRetrofitClient.getApi()

    @Provides
    @Singleton
    fun provideWebService(webService: WebServiceAPI): WebServiceRepository {
        return WebServiceRepository(webService)
    }
}