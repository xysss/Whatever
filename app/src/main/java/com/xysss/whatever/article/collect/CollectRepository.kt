package com.xysss.whatever.article.collect

import com.xysss.network.base.PlayAndroidNetwork
import com.xysss.whatever.main.login.fires
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author:bysd-2
 * Time:2021/3/1711:25
 */
@Singleton
class CollectRepository @Inject constructor() {

    /**
     * 获取收藏列表
     *
     * @param page 页码
     */
    fun getCollectList(page: Int) = fires { PlayAndroidNetwork.getCollectList(page) }

    suspend fun cancelCollects(id: Int) = PlayAndroidNetwork.cancelCollect(id)
    suspend fun toCollects(id: Int) = PlayAndroidNetwork.toCollect(id)

}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CollectRepositoryPoint {
    fun collectRepository(): CollectRepository
}