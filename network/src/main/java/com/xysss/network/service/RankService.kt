package com.xysss.network.service

import com.xysss.model.model.BaseModel
import com.xysss.model.model.RankData
import com.xysss.model.model.RankList
import com.xysss.model.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author:bysd-2
 * Time:2021/3/1711:04
 */
interface RankService {

    @GET("coin/rank/{page}/json")
    suspend fun getRankList(@Path("page") page: Int): BaseModel<RankData>

    @GET("lg/coin/userinfo/json")
    suspend fun getUserInfo(): BaseModel<UserInfo>

    @GET("lg/coin/list/{page}/json")
    suspend fun getUserRank(@Path("page") page: Int): BaseModel<RankList>

}