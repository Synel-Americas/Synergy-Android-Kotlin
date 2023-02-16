package com.synel.synergyt.synergykotlin.utils

import com.synel.synergyt.synergykotlin.datastore.*
import com.synel.synergyt.synergykotlin.model.database.model.TransactionDataEntity
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.AttLogsRequest
import com.synel.synergyt.synergykotlin.model.webservice.data.attlogs.Data
import com.synel.synergyt.synergykotlin.model.webservice.data.provision.*
import timber.log.Timber
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object MappingUtility {
    fun md5(str: String): ByteArray =
        MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))



    fun createAttendanceKey(attendanceData: Any): String {
        var uniqueAttendanceKey = md5(attendanceData.toString()).toHex()
        uniqueAttendanceKey = uniqueAttendanceKey.addCharAtIndex('-', 8)
            .addCharAtIndex('-', 13)
            .addCharAtIndex('-', 18)
            .addCharAtIndex('-', 23)
        return uniqueAttendanceKey
    }

    fun mapTransactionDataToAttlogsRequest(transactionData: TransactionDataEntity): AttLogsRequest {
        val data = Data(
            attendanceKey = transactionData.attendanceKey,
            customV1 = transactionData.customV1,
            customV2 = transactionData.customV2,
            customV3 = transactionData.customV3,
            customV4 = transactionData.customV4,
            customV5 = transactionData.customV5,
            customV6 = transactionData.customV6,
            customV7 = transactionData.customV7,
            customV8 = transactionData.customV8,
            customV9 = transactionData.customV9,
            customV10 = transactionData.customV10,
            customV11 = transactionData.customV11,
            empMaskStatus = transactionData.empMaskStatus,
            empTemperature = transactionData.empTemperature,
            employeeNumber = transactionData.employeeNumber,
            eventCode = transactionData.eventCode.value.toString(),
            fpIndex = transactionData.fpIndex,
            issueType = transactionData.issueType.value,
            nonCheckFlag = transactionData.nonCheckFlag,
            punchDate = transactionData.punchDate,
            punchMode = transactionData.punchMode.value,
            punch_photo_template = transactionData.punch_photo_template,
            status = transactionData.status.toString(),
            transactionId = transactionData.id.toInt(),
            verifyType = transactionData.verifyType.value.toString()
        ).also {
            if (it.attendanceKey.isEmpty()) {
                it.attendanceKey = createAttendanceKey(it)
            }
        }

        return AttLogsRequest(mutableListOf(data))
    }

    fun mapMultipleTransactionDataToAttlogsRequest(transactionDataList: List<TransactionDataEntity>): AttLogsRequest? {
        if (transactionDataList.isEmpty()) {
            return null
        }
        val dataList = mutableListOf<Data>()

        transactionDataList.forEach { it ->
            val data = Data(
                attendanceKey = it.attendanceKey,
                customV1 = it.customV1,
                customV2 = it.customV2,
                customV3 = it.customV3,
                customV4 = it.customV4,
                customV5 = it.customV5,
                customV6 = it.customV6,
                customV7 = it.customV7,
                customV8 = it.customV8,
                customV9 = it.customV9,
                customV10 = it.customV10,
                customV11 = it.customV11,
                empMaskStatus = it.empMaskStatus,
                empTemperature = it.empTemperature,
                employeeNumber = it.employeeNumber,
                eventCode = it.eventCode.value.toString(),
                fpIndex = it.fpIndex,
                issueType = it.issueType.value,
                nonCheckFlag = it.nonCheckFlag,
                punchDate = it.punchDate,
                punchMode = it.punchMode.value,
                punch_photo_template = it.punch_photo_template,
                status = it.status.toString(),
                transactionId = it.id.toInt(),
                verifyType = it.verifyType.value.toString()
            ).also {
                if (it.attendanceKey.isEmpty()) {
                    it.attendanceKey = createAttendanceKey(it)
                }
                dataList.add(it)
            }
        }
        return AttLogsRequest(dataList.toList())
    }

    fun mapProvisionResponseToProvision(provisionResponse: ProvisionResponse): Provision {
        val attRule = mapAttendanceRuleResponseToAttendanceRule(provisionResponse.attendanceRule)
        val punchMenuResponses =
            provisionResponse.punchMenus.map { mapPunchMenuResponseToPunchMenu(it) }
        val punchMenus = mapPunchMenusResponseToPunchMenus(punchMenuResponses)
        val deviceSetup = mapDeviceSetupResponseToAttendanceRule(provisionResponse.deviceSetup)
        val visitorAttestationModule =
            mapVisitorAttestationModuleResponseToVisitorAttestationModule(provisionResponse.visitorAttestationModule)

        return Provision.getDefaultInstance().toBuilder()
            .setPunchMenus(punchMenus)
            .setAttendanceRule(attRule)
            .setServerTime(provisionResponse.serverTime)
            .setDeviceLanguage(provisionResponse.deviceLanguage)
            .setTimezone(provisionResponse.timezone)
            .setHeartbeatInterval(provisionResponse.heartbeatInterval)
            .setTimesyncInterval(provisionResponse.timesyncInterval)
            .setHomeDateFormat(provisionResponse.homeDateFormat)
            .setHomeTimeFormat(provisionResponse.homeTimeFormat)
            .setInnerDateFormat(provisionResponse.innerDateFormat)
            .setInnerTimeFormat(provisionResponse.innerTimeFormat)
            .setDeviceName(provisionResponse.deviceName)
            .setClientLogoUrl(provisionResponse.clientLogoUrl)
            .setSoundLevel(provisionResponse.soundLevel)
            .setEnablePersonalMessage(provisionResponse.enablePersonalMessage)
            .setEnableMealAttestation(provisionResponse.enableMealAttestation)
            .setFpAttestationChoice(provisionResponse.fp_attestation_choice)
            .setBrightnessLevel(provisionResponse.brightnessLevel)
            .setMessagePrivilege(provisionResponse.messagePrivilege)
            .setMessageLockoutStartTime(provisionResponse.messageLockoutStartTime)
            .setMessageLockoutEndTime(provisionResponse.messageLockoutEndTime)
            .setEnableVisitorModule(
                provisionResponse.enableVisitorModule.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setDeviceSetup(deviceSetup)
            .setVisitorAttestationModule(visitorAttestationModule)
            .build()
    }

    fun mapAttendanceRuleResponseToAttendanceRule(attendanceRule: AttendanceRuleResponse): AttendanceRule {
//        Timber.d("attendanceRule = [ %s ]", attendanceRule);
        return AttendanceRule.getDefaultInstance().toBuilder()
            .setFastPunchEnable(attendanceRule.fastPunchEnable)
            .setEnableTimeLaborCode(attendanceRule.enableTimeLaborCode)
            .setNonScheduledDefaultPunchState(attendanceRule.nonScheduledDefaultPunchState)
            .setNonScheduledCalculationRule(attendanceRule.nonScheduledCalculationRule)
            .setNonScheduledLongestHour(attendanceRule.nonScheduledLongestHour)
            .setFastPunchDisplayMessage(attendanceRule.fastPunchDisplayMessage)
            .setLevel1Skip(attendanceRule.level1Skip)
            .setLevel2Skip(attendanceRule.level2Skip)
            .setLevel3Skip(attendanceRule.level3Skip)
            .setLevel4Skip(attendanceRule.level4Skip)
            .setLevel5Skip(attendanceRule.level5Skip)
            .setLevel6Skip(attendanceRule.level6Skip)
            .setEnablePhotoCapture(attendanceRule.enablePhotoCapture)
            .setConsecutivePunchLockoutPeriod(attendanceRule.consecutivePunchLockoutPeriod)
            .setMealBreakLockoutPeriod(attendanceRule.mealBreakLockoutPeriod)
            .setLogMealBreakLockedoutPunchData(attendanceRule.logMealBreakLockedoutPunchData)
            .setEnableClockLevelShiftScheduleLockout(attendanceRule.enableClockLevelShiftScheduleLockout)
            .setShiftStartGracePeriod(attendanceRule.shiftStartGracePeriod)
            .setShiftEndGracePeriod(attendanceRule.shiftEndGracePeriod)
            .setLogScheduleLockedoutPunchData(attendanceRule.logScheduleLockedoutPunchData)
            .setVerificationFailedPunchData(attendanceRule.verificationFailedPunchData)
            .setEnableMealLockout(attendanceRule.enableMealLockout)
            .setAcceptConsecutiveLockoutPunch(attendanceRule.acceptConsecutiveLockoutPunch)
            .setShiftScheduleDataUnavailablePunchAction(attendanceRule.shiftScheduleDataUnavailablePunchAction)
            .setEnableGeneralAttestation(
                attendanceRule.enableGeneralAttestation.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setDenyOfflineFastPunch(
                attendanceRule.denyOfflineFastPunch.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setShiftDataUnavailablePunchAction(attendanceRule.shiftDataUnavailablePunchAction)
            .setEnableGlobalLockoutOverride(
                attendanceRule.enableGlobalLockoutOverride.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setLockoutOverrideStartTime(attendanceRule.lockoutOverrideStartTime)
            .setLockoutOverrideEndTime(attendanceRule.lockoutOverrideEndTime)
            .setAcceptAttestationRejectedPunch(attendanceRule.acceptAttestationRejectedPunch)
            .setShowAttestationOnlyOncePerDay(
                attendanceRule.showAttestationOnlyOncePerDay.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setShowPhotoPreviewOnPunchFeedback(
                attendanceRule.showPhotoPreviewOnPunchFeedback.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .build()
    }

    fun mapBadgeSetupResponseToBadgeSetup(badgeSetupResponse: BadgeSetupResponse): BadgeSetup {
        return BadgeSetup.getDefaultInstance().toBuilder()
            .setSupportRFIDReader(
                badgeSetupResponse.supportRFIDReader.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setSupportSmartCardReader(
                badgeSetupResponse.supportSmartCardReader.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setSupportBarcodeReader(
                badgeSetupResponse.supportBarcodeReader.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setSupportMagneticReader(
                badgeSetupResponse.supportMagneticReader.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .build()
    }

    fun mapBarCodeSetupResponseToBarcodeSetup(barcodeCardSetupResponse: BarcodeCardSetupResponse): BarcodeCardSetup {
        return BarcodeCardSetup.getDefaultInstance().toBuilder()
            .setTruncateCardNumber(barcodeCardSetupResponse.truncateCardNumber)
            .setStartPosition(barcodeCardSetupResponse.startPosition)
            .setCardSize(barcodeCardSetupResponse.cardSize)
            .build()
    }

    fun mapCameraSetupResponseToCameraSetup(cameraSetupResponse: CameraSetupResponse): CameraSetup {
        return CameraSetup.getDefaultInstance().toBuilder()
            .setEnablePhotoCapture(
                cameraSetupResponse.enablePhotoCapture.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setDisplayPreview(cameraSetupResponse.displayPreview.equals("true", ignoreCase = true))
            .setPreviewTimeout(cameraSetupResponse.previewTimeout)
            .setPhotoCaptureCondition(cameraSetupResponse.photoCaptureCondition)
            .build()
    }

    fun mapAccessControlSetupResponsetoAccessControlSetup(accessControlSetupResponse: AccessControlSetupResponse): AccessControlSetup {
        return AccessControlSetup.getDefaultInstance().toBuilder()
            .setEnableAccessControl(
                accessControlSetupResponse.enableAccessControl.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setEnableRelay1(
                accessControlSetupResponse.enableRelay1.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setEnableRelay2(
                accessControlSetupResponse.enableRelay2.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setRelayTimeout(accessControlSetupResponse.relayTimeout)
            .build()
    }

    fun mapCodeLevelsResponseToCodeLevels(codeLevels: List<CodeLevel>?): CodeLevels {
        val codeLevelsBuilder = CodeLevels.getDefaultInstance().toBuilder()
        codeLevels?.forEach {
            codeLevelsBuilder.addCodeLevel(it)
        }
        return codeLevelsBuilder.build()
    }

    fun mapCodeLevelResponseToCodeLevel(codeLevelResponse: CodeLevelResponse): CodeLevel {
        Timber.d("codeLevelResponse = [ %s ]", codeLevelResponse)
        return CodeLevel.getDefaultInstance().toBuilder()
            .setLevel(codeLevelResponse.level)
            .setCode(codeLevelResponse.code)
            .setSkippable(codeLevelResponse.skippable)
            .build()
    }

    fun mapRuleResponseToRule(ruleResponse: List<String>): Rule {
        return Rule.getDefaultInstance().toBuilder()
            .addAllRule(ruleResponse)
            .build()
    }

    fun mapDeviceSetupResponseToAttendanceRule(deviceSetupResponse: DeviceSetupResponse): DeviceSetup {
        val thermalModule =
            mapThermalModuleResponseToThermalModule(deviceSetupResponse.thermalModule)
        val faceModule =
            mapFaceRecognitionModuleResponseToFaceRecognitionModule(deviceSetupResponse.faceRecognitionModule)
        val badgeSetup = mapBadgeSetupResponseToBadgeSetup(deviceSetupResponse.badgeSetup)
        val wiegandSetup = mapWiegandSetupResponseToWiegandSetup(deviceSetupResponse.wiegandSetup)
        val smartCardSetup =
            mapSmartCardSetupResponseToSmartCardSetup(deviceSetupResponse.smartCardSetup)
        val magneticCardSetup =
            mapMagneticCardSetupResponseToMagneticCardSetup(deviceSetupResponse.magneticCardSetup)
        val barcodeCardSetup =
            mapBarCodeSetupResponseToBarcodeSetup(deviceSetupResponse.barcodeCardSetup)
        val fingerPrintSetup =
            mapFingerprintSetupResponseToFingerprintSetup(
                deviceSetupResponse.fingerprintSetup ?: FingerprintSetupResponse()
            )
        val accessControlSetup =
            mapAccessControlSetupResponsetoAccessControlSetup(deviceSetupResponse.accessControlSetup)
        val cameraSetup = mapCameraSetupResponseToCameraSetup(deviceSetupResponse.cameraSetup)

        return DeviceSetup.getDefaultInstance().toBuilder()
            .setThermModule(thermalModule)
            .setFaceModule(faceModule)
            .setBadgeSetup(badgeSetup)
            .setWiegandSetup(wiegandSetup)
            .setSmartCardSetup(smartCardSetup)
            .setMagneticCardSetup(magneticCardSetup)
            .setBarcodeCardSetup(barcodeCardSetup)
            .setFingerModule(fingerPrintSetup)
            .setAccessControlSetup(accessControlSetup)
            .setCameraSetup(cameraSetup)
            .build()
    }

    fun mapFaceRecognitionModuleResponseToFaceRecognitionModule(faceRecognitionModuleResponse: FaceRecognitionModuleResponse): FaceRecognitionModule {
        return FaceRecognitionModule.getDefaultInstance().toBuilder()
            .setEnableFacialDetection(faceRecognitionModuleResponse.enableFacialDetection)
            .setFaceRecognitionThreshold(faceRecognitionModuleResponse.faceRecognitionThreshold)
            .setAcceptMaskFailurePunches(faceRecognitionModuleResponse.acceptMaskFailurePunches)
            .setMaskAbsentPunchAction(faceRecognitionModuleResponse.maskAbsentPunchAction)
            .setFaceEnrollThreshold(faceRecognitionModuleResponse.faceEnrollThreshold)
            .setEnableFaceRecognitionWithMask(faceRecognitionModuleResponse.enableFaceRecognitionWithMask)
            .build()
    }

    fun mapFingerprintSetupResponseToFingerprintSetup(fingerprintSetupResponse: FingerprintSetupResponse): FingerPrintSetup {
        return FingerPrintSetup.getDefaultInstance().toBuilder()
            .setEnrollmentThreshold(fingerprintSetupResponse.enrollmentThreshold)
            .setEnableFingerprintDetection(
                fingerprintSetupResponse.enableFingerprintDetection.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setMatchThreshold(fingerprintSetupResponse.matchThreshold)
            .setTrialTimesForMatching(fingerprintSetupResponse.trialTimesForMatching)
            .setDisplayFingerprintImage(
                fingerprintSetupResponse.displayFingerprintImage.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .build()
    }

    fun mapMagneticCardSetupResponseToMagneticCardSetup(magneticCardSetupResponse: MagneticCardSetupResponse): MagneticCardSetup {
        return MagneticCardSetup.getDefaultInstance().toBuilder()
            .setTruncateCardNumber(magneticCardSetupResponse.truncateCardNumber)
            .setStartPosition(magneticCardSetupResponse.startPosition)
            .setCardSize(magneticCardSetupResponse.cardSize)
            .build()
    }

    fun mapPunchMenuResponseToPunchMenu(punchMenuResponse: PunchMenuResponse): PunchMenu {
//        Timber.d("punchMenuResponse = [ %s ]", punchMenuResponse);
        val codeLevelResponses =
            punchMenuResponse.codeLevels?.map { mapCodeLevelResponseToCodeLevel(it) }
        val codeLevels = mapCodeLevelsResponseToCodeLevels(codeLevelResponses)
        val rules = mapRuleResponseToRule(punchMenuResponse.rules)

        return PunchMenu.getDefaultInstance().toBuilder()
            .setKeyNumber(punchMenuResponse.keyNumber)
            .setEventCode(punchMenuResponse.eventCode.toInt())
            .setLabel(punchMenuResponse.label)
            .setAction(punchMenuResponse.action)
            .setAttestProfile(punchMenuResponse.attest_profile)
            .setMenuIcon(punchMenuResponse.menuIcon)
            .setAddClosePunch(punchMenuResponse.addClosePunch)
            .setVisitorAllowed(punchMenuResponse.visitorAllowed)
            .setClosePunchEventCode(punchMenuResponse.closePunchEventCode)
            .setEnablePhotoCapture(punchMenuResponse.enablePhotoCapture)
            .setCodeLevels(codeLevels)
            .setRules(rules)
            .build()
    }

    fun mapPunchMenusResponseToPunchMenus(punchMenus: List<PunchMenu>): PunchMenus {
        val punchMenusBuilder = PunchMenus.getDefaultInstance().toBuilder()
        punchMenus.forEach {
            punchMenusBuilder.addPunchMenu(it)
        }
        return punchMenusBuilder.build()
    }

    fun mapSmartCardSetupResponseToSmartCardSetup(smartCardSetupResponse: SmartCardSetupResponse): SmartCardSetup {
        return SmartCardSetup.getDefaultInstance().toBuilder()
            .setStartBit(smartCardSetupResponse.startBit)
            .setEndBit(smartCardSetupResponse.endBit)
            .build()
    }

    fun mapThermalModuleResponseToThermalModule(thermalModuleResponse: ThermalModuleResponse): ThermalModule {
        return ThermalModule.getDefaultInstance().toBuilder()
            .setEnableThermalModule(thermalModuleResponse.enableThermalModule)
            .setRecordTemperature(thermalModuleResponse.recordTemperature)
            .setAcceptThermalFailurePunches(thermalModuleResponse.acceptThermalFailurePunches)
            .setTemperatureUnit(thermalModuleResponse.temperatureUnit)
            .setBodyTempThreshold(thermalModuleResponse.bodyTempThreshold)
            .setBodyTempThresholdLow(thermalModuleResponse.bodyTempThresholdLow)
            .setHighBodyTempPunchAction(thermalModuleResponse.highBodyTempPunchAction)
            .setLowBodyTempFastPunchAction(thermalModuleResponse.lowBodyTempPunchAction)
            .setHighBodyTempFastPunchAction(thermalModuleResponse.highBodyTempFastPunchAction)
            .setLowBodyTempFastPunchAction(thermalModuleResponse.lowBodyTempFastPunchAction)
            .setHighBodyTempOverrideScreenTimeout(thermalModuleResponse.highBodyTempOverrideScreenTimeout)
            .setEnableMotionBasedThermalDetection(
                thermalModuleResponse.enableMotionBasedThermalDetection.equals(
                    "true",
                    ignoreCase = true
                )
            )
            .setEyeDetectionUpperLimit(thermalModuleResponse.eyeDetectionUpperLimit)
            .setEyeDetectionLowerLimit(thermalModuleResponse.eyeDetectionLowerLimit)
            .setEyeDetectionLeftLimit(thermalModuleResponse.eyeDetectionLeftLimit)
            .setEyeDetectionRightLimit(thermalModuleResponse.eyeDetectionRightLimit)
            .setLowBodyTemperaturePunchAction(thermalModuleResponse.lowBodyTemperaturePunchAction)
            .build()
    }

    fun mapVisitorAttestationModuleResponseToVisitorAttestationModule(
        visitorAttestationModuleResponse: VisitorAttestationModuleResponse
    ): VisitorAttestationModule {
        return VisitorAttestationModule.getDefaultInstance().toBuilder()
            .setEnableVisitorAttestationModule(visitorAttestationModuleResponse.enableVisitorAttestationModule)
            .setEnableVisitorPhotoCapture(visitorAttestationModuleResponse.enableVisitorPhotoCapture)
            .setVisitorAttestationProfile(visitorAttestationModuleResponse.visitorAttestationProfile)
            .setEnableEmployeeVisitorLog(visitorAttestationModuleResponse.enableEmployeeVisitorLog)
            .build()
    }

    fun mapWiegandSetupResponseToWiegandSetup(wiegandSetupResponse: WiegandSetupResponse): WiegandSetup {
        return WiegandSetup.getDefaultInstance().toBuilder()
            .setCardFormat(wiegandSetupResponse.cardFormat)
            .setStartBit(wiegandSetupResponse.startBit)
            .setEndBit(wiegandSetupResponse.endBit)
            .build()
    }


}