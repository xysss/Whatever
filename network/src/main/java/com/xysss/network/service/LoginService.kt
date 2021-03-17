package com.xysss.network.service

import com.xysss.model.model.BaseModel
import com.xysss.model.model.Login
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Author:bysd-2
 * Time:2021/3/1711:03
 */
interface LoginService {

    @POST("user/login")
    suspend fun getLogin(
        @Query("username") username: String,
        @Query("password") password: String
    ): BaseModel<Login>

    @POST("user/register")
    suspend fun getRegister(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String
    ): BaseModel<Login>

    @GET("user/logout/json")
    suspend fun getLogout(): BaseModel<Any>


}