package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class DeviceSetup(
    val accessControlSetup: AccessControlSetup,
    val badgeSetup: BadgeSetup,
    val barcodeCardSetup: BarcodeCardSetup,
    val cameraSetup: CameraSetup,
    val faceRecognitionModule: FaceRecognitionModule,
    val fingerprintSetup: FingerprintSetup,
    val magneticCardSetup: MagneticCardSetup,
    val smartCardSetup: SmartCardSetup,
    val thermalModule: ThermalModule,
    val wiegandSetup: WiegandSetup
)