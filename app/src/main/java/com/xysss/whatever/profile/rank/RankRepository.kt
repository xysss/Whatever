package com.xysss.whatever.profile.rank

import com.xysss.network.base.PlayAndroidNetwork
import com.xysss.whatever.main.login.fires
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:21
 */

@ActivityRetainedScoped
class RankRepository @Inject constructor(){

    /**
     * 获取排行榜列表
     *
     * @param page 页码
     */
    fun getRankList(page: Int) = fires { PlayAndroidNetwork.getRankList(page) }

    /**
     * 获取个人积分获取列表
     *
     * @param page 页码
     */
    fun getUserRank(page: Int) = fires { PlayAndroidNetwork.getUserRank(page) }

    /**
     * 获取个人积分信息
     */
    fun getUserInfo() = fires { PlayAndroidNetwork.getUserInfo() }


}