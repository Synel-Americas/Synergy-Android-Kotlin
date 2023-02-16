package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class DeviceSetupResponse(
    val accessControlSetup: AccessControlSetupResponse = AccessControlSetupResponse(),
    val badgeSetup: BadgeSetupResponse = BadgeSetupResponse(),
    val barcodeCardSetup: BarcodeCardSetupResponse = BarcodeCardSetupResponse(),
    val cameraSetup: CameraSetupResponse = CameraSetupResponse(),
    val faceRecognitionModule: FaceRecognitionModuleResponse = FaceRecognitionModuleResponse(),
    val fingerprintSetup: FingerprintSetupResponse? = FingerprintSetupResponse(),
    val magneticCardSetup: MagneticCardSetupResponse = MagneticCardSetupResponse(),
    val smartCardSetup: SmartCardSetupResponse = SmartCardSetupResponse(),
    val thermalModule: ThermalModuleResponse = ThermalModuleResponse(),
    val wiegandSetup: WiegandSetupResponse = WiegandSetupResponse()
)