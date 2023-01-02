package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class ThermalModule(
    val acceptThermalFailurePunches: Boolean,
    val bodyTempThreshold: String,
    val bodyTempThresholdLow: String,
    val enableMotionBasedThermalDetection: String,
    val enableThermalModule: Boolean,
    val eyeDetectionLeftLimit: String,
    val eyeDetectionLowerLimit: String,
    val eyeDetectionRightLimit: String,
    val eyeDetectionUpperLimit: String,
    val highBodyTempFastPunchAction: String,
    val highBodyTempOverrideScreenTimeout: Int,
    val highBodyTempPunchAction: String,
    val lowBodyTempFastPunchAction: String,
    val lowBodyTempPunchAction: String,
    val lowBodyTemperaturePunchAction: String,
    val motionBasedThermalSamples: String,
    val recordTemperature: Boolean,
    val temperatureUnit: String
)