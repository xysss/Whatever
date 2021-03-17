package com.xysss.whatever.official

import android.app.Application
import com.xysss.core.util.DataStoreUtils
import com.xysss.model.pojo.QueryArticle
import com.xysss.model.room.PlayDatabase
import com.xysss.model.room.entity.OFFICIAL
import com.xysss.network.base.PlayAndroidNetwork
import com.xysss.whatever.home.DOWN_OFFICIAL_ARTICLE_TIME
import com.xysss.whatever.home.FOUR_HOUR
import com.xysss.whatever.main.login.fire
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:33
 */

@ActivityRetainedScoped
class OfficialRepository @Inject constructor(application: Application) {

    private val projectClassifyDao = PlayDatabase.getDatabase(application).projectClassifyDao()
    private val articleListDao = PlayDatabase.getDatabase(application).browseHistoryDao()

    /**
     * 获取公众号标题列表
     */
    fun getWxArticleTree(isRefresh: Boolean) = fire {
        val projectClassifyLists = projectClassifyDao.getAllOfficial()
        if (projectClassifyLists.isNotEmpty() && !isRefresh) {
            Result.success(projectClassifyLists)
        } else {
            val projectTree = PlayAndroidNetwork.getWxArticleTree()
            if (projectTree.errorCode == 0) {
                val projectList = projectTree.data
                projectClassifyDao.insertList(projectList)
                Result.success(projectList)
            } else {
                Result.failure(RuntimeException("response status is ${projectTree.errorCode}  msg is ${projectTree.errorMsg}"))
            }
        }

    }

    /**
     * 获取具体公众号文章列表
     * @param query 查询
     */
    fun getWxArticle(query: QueryArticle) = fire {
        if (query.page == 1) {
            val dataStore = DataStoreUtils
            val articleListForChapterId =
                articleListDao.getArticleListForChapterId(OFFICIAL, query.cid)
            var downArticleTime = 0L
            dataStore.readLongFlow(DOWN_OFFICIAL_ARTICLE_TIME, System.currentTimeMillis()).first {
                downArticleTime = it
                true
            }
            if (articleListForChapterId.isNotEmpty() && downArticleTime > 0 && downArticleTime - System.currentTimeMillis() < FOUR_HOUR && !query.isRefresh) {
                Result.success(articleListForChapterId)
            } else {
                val projectTree = PlayAndroidNetwork.getWxArticle(query.page, query.cid)
                if (projectTree.errorCode == 0) {
                    if (articleListForChapterId.isNotEmpty() && articleListForChapterId[0].link == projectTree.data.datas[0].link && !query.isRefresh) {
                        Result.success(articleListForChapterId)
                    } else {
                        projectTree.data.datas.forEach {
                            it.localType = OFFICIAL
                        }
                        DataStoreUtils.saveLongData(DOWN_OFFICIAL_ARTICLE_TIME, System.currentTimeMillis())
                        if (query.isRefresh) {
                            articleListDao.deleteAll(OFFICIAL, query.cid)
                        }
                        articleListDao.insertList(projectTree.data.datas)
                        Result.success(projectTree.data.datas)
                    }
                } else {
                    Result.failure(RuntimeException("response status is ${projectTree.errorCode}  msg is ${projectTree.errorMsg}"))
                }
            }
        } else {
            val projectTree = PlayAndroidNetwork.getWxArticle(query.page, query.cid)
            if (projectTree.errorCode == 0) {
                Result.success(projectTree.data.datas)
            } else {
                Result.failure(RuntimeException("response status is ${projectTree.errorCode}  msg is ${projectTree.errorMsg}"))
            }
        }

    }

}