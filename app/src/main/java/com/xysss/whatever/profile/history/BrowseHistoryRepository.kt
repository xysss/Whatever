package com.xysss.whatever.profile.history

import android.app.Application
import com.xysss.model.room.PlayDatabase
import com.xysss.model.room.entity.HISTORY
import com.xysss.whatever.main.login.fire
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:33
 */

@ActivityRetainedScoped
class BrowseHistoryRepository @Inject constructor(val application: Application) {

    private val browseHistoryDao = PlayDatabase.getDatabase(application).browseHistoryDao()

    /**
     * 获取历史记录列表
     */
    fun getBrowseHistory(page: Int) = fire {
        val projectClassifyLists = browseHistoryDao.getHistoryArticleList((page - 1) * 20,HISTORY)
        if (projectClassifyLists.isNotEmpty()) {
            Result.success(projectClassifyLists)
        } else {
            Result.failure(RuntimeException("response status is "))
        }

    }

}