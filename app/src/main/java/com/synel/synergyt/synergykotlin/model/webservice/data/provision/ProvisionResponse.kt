package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class ProvisionResponse(
    val attendanceRule: AttendanceRule,
    val brightnessLevel: Int,
    val clientLogoUrl: String,
    val deviceLanguage: String,
    val deviceName: String,
    val deviceSetup: DeviceSetup,
    val enableMealAttestation: Boolean,
    val enablePersonalMessage: Boolean,
    val enableVisitorModule: String,
    val fp_attestation_choice: Boolean,
    val heartbeatInterval: Int,
    val homeDateFormat: String,
    val homeTimeFormat: String,
    val innerDateFormat: String,
    val innerTimeFormat: String,
    val messageLockoutEndTime: String,
    val messageLockoutStartTime: String,
    val messagePrivilege: String,
    val punchMenus: List<PunchMenu>,
    val serverTime: String,
    val soundLevel: Int,
    val timesyncInterval: Int,
    val timezone: String,
    val visitorAttestationModule: VisitorAttestationModule
)