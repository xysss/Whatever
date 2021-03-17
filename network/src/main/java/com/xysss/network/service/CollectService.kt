package com.xysss.network.service

import com.xysss.model.model.BaseModel
import com.xysss.model.model.Collect
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Author:bysd-2
 * Time:2021/3/1711:02
 */
interface CollectService {

    @GET("lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): BaseModel<Collect>

    @POST("lg/collect/{id}/json")
    suspend fun toCollect(@Path("id") id: Int): BaseModel<Any>

    @POST("lg/uncollect_originId/{id}/json")
    suspend fun cancelCollect(@Path("id") id: Int): BaseModel<Any>

}