package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class FaceRecognitionModule(
    val acceptMaskFailurePunches: Boolean,
    val enableFaceRecognitionWithMask: Boolean,
    val enableFacialDetection: Boolean,
    val faceEnrollThreshold: Int,
    val faceRecognitionThreshold: Int,
    val maskAbsentPunchAction: String
)