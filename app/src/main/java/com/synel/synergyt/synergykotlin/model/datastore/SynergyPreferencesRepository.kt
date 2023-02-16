package com.synel.synergyt.synergykotlin.model.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import com.synel.synergyt.synergykotlin.datastore.AttendanceRule
import com.synel.synergyt.synergykotlin.datastore.DeviceSetup
import com.synel.synergyt.synergykotlin.datastore.Provision
import com.synel.synergyt.synergykotlin.datastore.PunchMenus
import com.synel.synergyt.synergykotlin.datastore.SynergyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class SynergyPreferencesRepository @Inject constructor(
    private val context: Application,
    private val synergyPreferencesDataStore: DataStore<SynergyPreferences>

) : PreferencesRepository {
    override lateinit var synergyPreferences: Flow<SynergyPreferences>
        override lateinit var provision: Flow<Provision>
        override lateinit var attendanceRule: Flow<AttendanceRule>
        override lateinit var punchMenus: Flow<PunchMenus>
        override lateinit var deviceSetup: Flow<DeviceSetup>

    init {
        try {
            synergyPreferences =
                synergyPreferencesDataStore.data.catch { exc ->
                    if (exc is IOException) {
                        Timber.e(exc)
                        emit(SynergyPreferences.getDefaultInstance())
                    } else {
                        throw exc
                    }
                }
            provision = synergyPreferences.map { it.provision }
            attendanceRule = provision.map { it.attendanceRule }
            punchMenus = provision.map { it.punchMenus }
            deviceSetup = provision.map { it.deviceSetup }
        } catch (exc: Exception) {
            Timber.e(exc)
        }
    }

    override suspend fun updateProvision(provision: Provision) {
        synergyPreferencesDataStore.updateData { it.toBuilder().setProvision(provision).build() }
    }

}