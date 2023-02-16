package com.synel.synergyt.synergykotlin.model.webservice

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.synel.synergyt.synergykotlin.model.webservice.base.BaseResponseBody
import com.synel.synergyt.synergykotlin.model.webservice.data.deserializer.HeartBeatDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object SynergyRetrofitClient {

    private var urlChanged = false
    private var endPointUrl = WebServiceAPI.BASE_URL
    private var retrofitClient = Retrofit.Builder()
        .baseUrl(endPointUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(buildGsonConverter())
        .build()
    private var webServiceApi = retrofitClient.create(WebServiceAPI::class.java)

    fun getClient(): Retrofit {
        if (urlChanged) {
            urlChanged = false
            retrofitClient = Retrofit.Builder()
                .baseUrl(endPointUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(buildGsonConverter())
                .build()
            webServiceApi = retrofitClient.create(WebServiceAPI::class.java)
        }

        return retrofitClient
    }

    fun getApi(): WebServiceAPI {
        return webServiceApi
    }

    fun setUrl(url: String) {
        if (url != endPointUrl) {
            urlChanged = true
            endPointUrl = url
        }
    }

    private fun buildGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter( object : TypeToken<MutableList<BaseResponseBody?>?>() {}.type, HeartBeatDeserializer())
        gsonBuilder.disableHtmlEscaping()
        gsonBuilder.setLenient()

        return GsonConverterFactory.create(gsonBuilder.create())
    }

}