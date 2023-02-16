package com.synel.synergyt.synergykotlin.model.webservice.data.orgCode

import com.synel.synergyt.synergykotlin.model.webservice.base.BaseDetails

data class Details(
    override val command: String,
    val masterOrgCode: MasterOrgCode,
    val orgCodeDetails: List<OrgCodeDetail>
): BaseDetails()