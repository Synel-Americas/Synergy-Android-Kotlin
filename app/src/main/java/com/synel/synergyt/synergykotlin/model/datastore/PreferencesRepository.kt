package com.synel.synergyt.synergykotlin.model.datastore

import com.synel.synergyt.synergykotlin.datastore.*
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    var synergyPreferences: Flow<SynergyPreferences>
    var provision: Flow<Provision>
    var attendanceRule: Flow<AttendanceRule>
    var punchMenus: Flow<PunchMenus>
    var deviceSetup: Flow<DeviceSetup>

    suspend fun updateProvision(provision: Provision)
}