package com.synel.synergyt.synergykotlin.model.webservice.data.provision

data class BadgeSetup(
    val supportBarcodeReader: String,
    val supportMagneticReader: String,
    val supportRFIDReader: String,
    val supportSmartCardReader: String
)