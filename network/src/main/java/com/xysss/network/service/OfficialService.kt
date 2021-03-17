package com.xysss.network.service

import com.xysss.model.model.ArticleList
import com.xysss.model.model.BaseModel
import com.xysss.model.room.entity.ProjectClassify
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author:bysd-2
 * Time:2021/3/1711:03
 */
interface OfficialService {

    @GET("wxarticle/chapters/json")
    suspend fun getWxArticleTree(): BaseModel<List<ProjectClassify>>

    @GET("wxarticle/list/{cid}/{page}/json")
    suspend fun getWxArticle(@Path("page") page: Int, @Path("cid") cid: Int): BaseModel<ArticleList>

}