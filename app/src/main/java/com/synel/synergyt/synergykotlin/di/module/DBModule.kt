package com.synel.synergyt.synergykotlin.di.module

import android.app.Application
import androidx.room.Room
import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.database.dao.EmployeeDao
import com.synel.synergyt.synergykotlin.model.database.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

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
    @Singleton
    fun provideEmployeeDaoManager(appDatabase: AppDatabase): EmployeeRepository {
        return EmployeeRepository(appDatabase)
    }

}