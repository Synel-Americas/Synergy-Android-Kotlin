package com.synel.synergyt.synergykotlin.model.database.converters

import androidx.room.TypeConverter
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.math.BigInteger
import java.time.ZoneId
import java.time.ZonedDateTime


class GenericsConverter{

    private val zonedDateTimeGson = GsonBuilder().registerTypeAdapter(
        ZonedDateTime::class.java,
        JsonDeserializer { json, _, _ -> //ZonedDateTime is saved in the following format:
            //{"dateTime":{
            //"date":{"day":INTEGER,"month":INTEGER,"year":INTEGER},
            //"time":{"hour":INTEGER,"minute":INTEGER,"nano":INTEGER,"second":INTEGER}},
            //"offset":{"totalSeconds":INTEGER},
            //"zone":{"id":STRING}}
            val zonedDateTimeJson = json.asJsonObject
            val dateTimeObj = zonedDateTimeJson.getAsJsonObject("dateTime")
            val dateObj = dateTimeObj.getAsJsonObject("date")
            val timeObj = dateTimeObj.getAsJsonObject("time")
            val zoneObj = zonedDateTimeJson.getAsJsonObject("zone")
            ZonedDateTime.of(
                dateObj["year"].asInt,
                dateObj["month"].asInt,
                dateObj["day"].asInt,
                timeObj["hour"].asInt,
                timeObj["minute"].asInt,
                timeObj["second"].asInt,
                timeObj["nano"].asInt,
                ZoneId.of(zoneObj["id"].asString)
            )
        }).create()

@TypeConverter
fun bigIntegerToString(`val`: BigInteger?): String? {
    return `val`?.toString()
}

@TypeConverter
fun stringToBigInteger(`val`: String?): BigInteger? {
    var res: BigInteger? = null
    try {
        res = `val`?.let { BigInteger(it) }
    } catch (e: Exception) {
        Timber.e(e)
    }
    return res
}

@TypeConverter
fun gsonToList(list: String?): List<String?>? {
    return if (list == null) null else Gson().fromJson(
        list,
        object : TypeToken<List<String?>?>() {}.type
    )
}

@TypeConverter
fun listToGson(list: List<String?>?): String? {
    return if (list == null) null else Gson().toJson(list)
}

@TypeConverter
fun stringToZonedDate(data: String?): ZonedDateTime? {
    return if (data == null) null else zonedDateTimeGson.fromJson(
        data,
        object : TypeToken<ZonedDateTime?>() {}.type
    )
}

@TypeConverter
fun zonedDateToString(dateTime: ZonedDateTime?): String? {
    return if (dateTime == null) null else zonedDateTimeGson.toJson(dateTime)
}

}
