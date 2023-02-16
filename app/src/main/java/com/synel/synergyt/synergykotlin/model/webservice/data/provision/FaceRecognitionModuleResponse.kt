package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class FaceRecognitionModuleResponse(
    val acceptMaskFailurePunches: Boolean = false,
    val enableFaceRecognitionWithMask: Boolean = false,
    val enableFacialDetection: Boolean = false,
    val faceEnrollThreshold: Int = 0,
    val faceRecognitionThreshold: Int = 0,
    val maskAbsentPunchAction: String = ""
)