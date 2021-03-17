package com.xysss.model.model

/**
 * Author:bysd-2
 * Time:2021/3/1710:51
 */

data class RankData(
    val curPage: Int,
    val datas: List<Rank>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class Rank(
    val coinCount: Int,
    val level: Int,
    val rank: String,
    val userId: Int,
    val username: String
)
