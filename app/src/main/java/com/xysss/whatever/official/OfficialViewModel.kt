package com.xysss.whatever.official

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.room.entity.ProjectClassify
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:33
 */

@ActivityScoped
class OfficialViewModel @ViewModelInject constructor(
    private val officialRepository: OfficialRepository
) : BaseAndroidViewModel<List<ProjectClassify>, Unit, Boolean>() {

    var position = 0

    override fun getData(page: Boolean): LiveData<Result<List<ProjectClassify>>> {
        return officialRepository.getWxArticleTree(page)
    }
}