package com.synel.synergyt.synergykotlin.di.module

import android.app.Application
import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.synel.synergyt.synergykotlin.CoreApplication
import com.synel.synergyt.synergykotlin.datastore.SynergyPreferences
import com.synel.synergyt.synergykotlin.di.MainViewModelFactory
import com.synel.synergyt.synergykotlin.model.database.AppDatabase
import com.synel.synergyt.synergykotlin.model.webservice.WebServiceRepository
import com.synel.synergyt.synergykotlin.model.datastore.PreferencesRepository
import com.synel.synergyt.synergykotlin.model.datastore.SynergyPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

    @Provides
    fun provideMainViewModelFactory(
        database: AppDatabase,
        webRepository: WebServiceRepository,
        dataRepo: SynergyPreferencesRepository
    ): MainViewModelFactory {
        return MainViewModelFactory(database, webRepository, dataRepo)
    }



    @Provides
    @Reusable
    fun provideProtoDataStore(application: Application) =
        application.synergyPreferencesDataStore

    @Provides
    @Reusable
    internal fun providesPreferencesRepository(
        application: Application,
        synergyPreferencesDataStore: DataStore<SynergyPreferences>
    ): PreferencesRepository {
        return SynergyPreferencesRepository(
            application,
            synergyPreferencesDataStore
        ) as PreferencesRepository
    }


    object PreferencesSerializer : Serializer<SynergyPreferences> {

        override val defaultValue: SynergyPreferences = SynergyPreferences.getDefaultInstance()
        override suspend fun readFrom(input: InputStream): SynergyPreferences {
            try {
                return SynergyPreferences.parseFrom(input)
            } catch (e: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", e)
            }
        }

        override suspend fun writeTo(t: SynergyPreferences, output: OutputStream) {
            t.writeTo(output)
        }
    }

    private val Application.synergyPreferencesDataStore: DataStore<SynergyPreferences> by dataStore(
        fileName = "SynergyPreferences.pb",
        serializer = PreferencesSerializer
    )
}