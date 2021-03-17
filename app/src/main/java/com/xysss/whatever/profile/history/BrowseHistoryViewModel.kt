package com.xysss.whatever.profile.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.room.entity.Article
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:33
 */

@ActivityScoped
class BrowseHistoryViewModel @ViewModelInject constructor(
    private val browseHistoryRepository: BrowseHistoryRepository
) : BaseAndroidViewModel<List<Article>, Article, Int>() {

    override fun getData(page: Int): LiveData<Result<List<Article>>> {
        return browseHistoryRepository.getBrowseHistory(page)
    }

}