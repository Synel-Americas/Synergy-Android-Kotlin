package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class ThermalModuleResponse(
    val acceptThermalFailurePunches: Boolean = false,
    val bodyTempThreshold: String = "",
    val bodyTempThresholdLow: String = "",
    val enableMotionBasedThermalDetection: String = "",
    val enableThermalModule: Boolean = false,
    val eyeDetectionLeftLimit: String = "",
    val eyeDetectionLowerLimit: String = "",
    val eyeDetectionRightLimit: String = "",
    val eyeDetectionUpperLimit: String = "",
    val highBodyTempFastPunchAction: String = "",
    val highBodyTempOverrideScreenTimeout: Int = 0,
    val highBodyTempPunchAction: String = "",
    val lowBodyTempFastPunchAction: String = "",
    val lowBodyTempPunchAction: String = "",
    val lowBodyTemperaturePunchAction: String = "",
    val motionBasedThermalSamples: String = "",
    val recordTemperature: Boolean = false,
    val temperatureUnit: String = ""
)