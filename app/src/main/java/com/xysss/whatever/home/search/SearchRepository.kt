package com.xysss.whatever.home.search

import android.app.Application
import com.xysss.model.room.PlayDatabase
import com.xysss.network.base.PlayAndroidNetwork
import com.xysss.whatever.main.login.fire
import com.xysss.whatever.main.login.fires
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:29
 */
@ActivityRetainedScoped
class SearchRepository @Inject constructor(application: Application) {

    private val hotKeyDao = PlayDatabase.getDatabase(application).hotKeyDao()

    /**
     * 获取搜索热词
     */
    fun getHotKey() = fire {
        val hotKeyList = hotKeyDao.getHotKeyList()
        if (hotKeyList.isNotEmpty()) {
            Result.success(hotKeyList)
        } else {
            val projectTree = PlayAndroidNetwork.getHotKey()
            if (projectTree.errorCode == 0) {
                val hotKeyLists = projectTree.data
                hotKeyDao.insertList(hotKeyLists)
                Result.success(hotKeyLists)
            } else {
                Result.failure(RuntimeException("response status is ${projectTree.errorCode}  msg is ${projectTree.errorMsg}"))
            }
        }
    }

    /**
     * 获取搜索结果
     */
    fun getQueryArticleList(page: Int, k: String) = fires {
        PlayAndroidNetwork.getQueryArticleList(page, k)
    }

}