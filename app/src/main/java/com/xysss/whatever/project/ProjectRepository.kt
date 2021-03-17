package com.xysss.whatever.project

import android.app.Application
import com.xysss.core.util.DataStoreUtils
import com.xysss.model.pojo.QueryArticle
import com.xysss.model.room.PlayDatabase
import com.xysss.model.room.entity.PROJECT
import com.xysss.network.base.PlayAndroidNetwork
import com.xysss.whatever.home.DOWN_PROJECT_ARTICLE_TIME
import com.xysss.whatever.home.FOUR_HOUR
import com.xysss.whatever.main.login.fire
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:24
 */

@ActivityRetainedScoped
class ProjectRepository @Inject constructor(val application: Application) {

    private val projectClassifyDao = PlayDatabase.getDatabase(application).projectClassifyDao()
    private val articleListDao = PlayDatabase.getDatabase(application).browseHistoryDao()

    /**
     * 获取项目标题列表
     */
    fun getProjectTree(isRefresh: Boolean) = fire {
        val projectClassifyLists = projectClassifyDao.getAllProject()
        if (projectClassifyLists.isNotEmpty() && !isRefresh) {
            Result.success(projectClassifyLists)
        } else {
            val projectTree = PlayAndroidNetwork.getProjectTree()
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
     * 获取项目具体文章列表
     * @param query 查询类
     */
    fun getProject(query: QueryArticle) = fire {
        if (query.page == 1) {
            val dataStore = DataStoreUtils
            val articleListForChapterId =
                articleListDao.getArticleListForChapterId(PROJECT, query.cid)
            var downArticleTime = 0L
            dataStore.readLongFlow(DOWN_PROJECT_ARTICLE_TIME, System.currentTimeMillis()).first {
                downArticleTime = it
                true
            }
            if (articleListForChapterId.isNotEmpty() && downArticleTime > 0 && downArticleTime - System.currentTimeMillis() < FOUR_HOUR && !query.isRefresh) {
                Result.success(articleListForChapterId)
            } else {
                val projectTree = PlayAndroidNetwork.getProject(query.page, query.cid)
                if (projectTree.errorCode == 0) {
                    if (articleListForChapterId.isNotEmpty() && articleListForChapterId[0].link == projectTree.data.datas[0].link && !query.isRefresh) {
                        Result.success(articleListForChapterId)
                    } else {
                        projectTree.data.datas.forEach {
                            it.localType = PROJECT
                        }
                        dataStore.saveLongData(
                            DOWN_PROJECT_ARTICLE_TIME,
                            System.currentTimeMillis()
                        )
                        if (query.isRefresh) {
                            articleListDao.deleteAll(PROJECT, query.cid)
                        }
                        articleListDao.insertList(projectTree.data.datas)
                        Result.success(projectTree.data.datas)
                    }
                } else {
                    Result.failure(RuntimeException("response status is ${projectTree.errorCode}  msg is ${projectTree.errorMsg}"))
                }
            }
        } else {
            val projectTree = PlayAndroidNetwork.getProject(query.page, query.cid)
            if (projectTree.errorCode == 0) {
                Result.success(projectTree.data.datas)
            } else {
                Result.failure(RuntimeException("response status is ${projectTree.errorCode}  msg is ${projectTree.errorMsg}"))
            }
        }
    }

}