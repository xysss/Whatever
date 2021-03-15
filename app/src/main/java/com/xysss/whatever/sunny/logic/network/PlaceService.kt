package com.xysss.whatever.sunny.logic.network

import com.xysss.whatever.sunny.logic.model.PlaceResponse
import com.xysss.whatever.App
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService{
    @GET("v2/place?token=${App.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}