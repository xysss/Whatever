package com.xysss.network.service

import com.xysss.model.model.ArticleList
import com.xysss.model.model.BaseModel
import com.xysss.model.room.entity.ProjectClassify
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Author:bysd-2
 * Time:2021/3/1711:04
 */
interface ProjectService {

    @GET("project/tree/json")
    suspend fun getProjectTree(): BaseModel<List<ProjectClassify>>

    @GET("project/list/{page}/json")
    suspend fun getProject(@Path("page") page: Int, @Query("cid") cid: Int): BaseModel<ArticleList>

}