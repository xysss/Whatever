package com.xysss.whatever.article.collect

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseViewModel
import com.xysss.model.model.Collect
import com.xysss.model.model.CollectX
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:25
 */
@ActivityScoped
class CollectListViewModel @ViewModelInject constructor(private val collectRepository: CollectRepository) :
    BaseViewModel<Collect, CollectX, Int>() {

    override fun getData(page: Int): LiveData<Result<Collect>> {
        return collectRepository.getCollectList(page - 1)
    }


}