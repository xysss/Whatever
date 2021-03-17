package com.xysss.whatever.official.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.pojo.QueryArticle
import com.xysss.model.room.entity.Article
import com.xysss.whatever.official.OfficialRepository
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:32
 */

@FragmentScoped
class OfficialListViewModel @ViewModelInject constructor(
    private val officialRepository: OfficialRepository
) : BaseAndroidViewModel<List<Article>, Article, QueryArticle>() {

    override fun getData(page: QueryArticle): LiveData<Result<List<Article>>> {
        return officialRepository.getWxArticle(page)
    }

}

