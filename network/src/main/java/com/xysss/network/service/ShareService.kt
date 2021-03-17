package com.xysss.network.service

import com.xysss.model.model.BaseModel
import com.xysss.model.model.ShareModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author:bysd-2
 * Time:2021/3/1711:04
 */
interface ShareService {

    @GET("user/{cid}/share_articles/{page}/json")
    suspend fun getShareList(@Path("cid") cid: Int, @Path("page") page: Int): BaseModel<ShareModel>

    @GET("user/lg/private_articles/{page}/json")
    suspend fun getMyShareList(@Path("page") page: Int): BaseModel<ShareModel>

    @POST("lg/user_article/delete/{cid}/json")
    suspend fun deleteMyArticle(@Path("cid") cid: Int): BaseModel<Any>

    @POST("lg/user_article/add/json")
    suspend fun shareArticle(
        @Query("title") title: String,
        @Query("link") link: String
    ): BaseModel<Any>

}