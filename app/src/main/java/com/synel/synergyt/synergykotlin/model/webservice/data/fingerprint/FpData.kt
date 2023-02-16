package com.synel.synergyt.synergykotlin.model.webservice.data.fingerprint

data class FpData(
    val employeeNumber: String,
    val fpAttestationAccepted: Boolean,
    val fpIndex: String,
    val fpTemplate: String,
    val primaryKey: String,
    val size: Int
)