package com.synel.synergyt.synergykotlin.model.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.synel.synergyt.synergykotlin.model.database.converters.BaseResponseBodyConverter
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.WSCMD
import java.time.ZonedDateTime

@Entity(tableName = "WebserviceCommand")
@TypeConverters(BaseResponseBodyConverter::class)
data class WebserviceCommandEntity(
    @PrimaryKey
    val id: Int,
    val isProcessed: Boolean,
    val commandType: WSCMD,
    val receiveDate: ZonedDateTime,
    val commandObject: BaseResponseBody

)