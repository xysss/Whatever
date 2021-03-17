package com.xysss.model.model

import com.xysss.model.room.entity.Article

/**
 * Author:bysd-2
 * Time:2021/3/1710:51
 */


data class ShareModel(
    val coinInfo: CoinInfo,
    val shareArticles: ShareArticles
)

data class CoinInfo(
    val coinCount: Int,
    val level: Int,
    val rank: String,
    val userId: Int,
    val username: String
)

data class ShareArticles(
    val curPage: Int,
    val datas: List<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)