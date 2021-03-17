package com.xysss.model.model

import com.xysss.model.room.entity.Article

/**
 * Author:bysd-2
 * Time:2021/3/1710:49
 */
data class ArticleList(
    val curPage: Int,
    val datas: List<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)