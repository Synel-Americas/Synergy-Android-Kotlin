package com.synel.synergyt.synergykotlin.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.database.EmployeeDao
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(WebServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWebServiceApi(retrofit: Retrofit): WebServiceAPI =
        retrofit.create(WebServiceAPI::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideEmployeeDao(database: AppDatabase): EmployeeDao {
        return database.employeeDao()
    }
    @Provides
    fun provideMainViewModelFactory(database: AppDatabase, apiService: WebServiceAPI): MainViewModelFactory {
        return MainViewModelFactory(database, apiService)
    }
}