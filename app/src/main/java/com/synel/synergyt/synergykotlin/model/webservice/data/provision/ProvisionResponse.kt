package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class ProvisionResponse(
    val attendanceRule: AttendanceRuleResponse = AttendanceRuleResponse(),
    val brightnessLevel: Int = 0,
    val clientLogoUrl: String = "",
    val deviceLanguage: String = "",
    val deviceName: String = "",
    val deviceSetup: DeviceSetupResponse = DeviceSetupResponse(),
    val enableMealAttestation: Boolean = false,
    val enablePersonalMessage: Boolean = false,
    val enableVisitorModule: String = "",
    val fp_attestation_choice: Boolean = false,
    val heartbeatInterval: Int = 0,
    val homeDateFormat: String = "",
    val homeTimeFormat: String = "",
    val innerDateFormat: String = "",
    val innerTimeFormat: String = "",
    val messageLockoutEndTime: String = "",
    val messageLockoutStartTime: String = "",
    val messagePrivilege: String = "",
    val punchMenus: List<PunchMenuResponse> = mutableListOf(PunchMenuResponse()),
    val serverTime: String = "",
    val soundLevel: Int = 0,
    val timesyncInterval: Int = 0,
    val timezone: String = "",
    val visitorAttestationModule: VisitorAttestationModuleResponse = VisitorAttestationModuleResponse()
)