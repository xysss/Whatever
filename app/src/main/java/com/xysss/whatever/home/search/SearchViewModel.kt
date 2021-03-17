package com.xysss.whatever.home.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.room.entity.HotKey
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:29
 */
@ActivityScoped
class SearchViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
) : BaseAndroidViewModel<List<HotKey>, HotKey, Boolean>() {

    override fun getData(page: Boolean): LiveData<Result<List<HotKey>>> {
        return searchRepository.getHotKey()
    }

}