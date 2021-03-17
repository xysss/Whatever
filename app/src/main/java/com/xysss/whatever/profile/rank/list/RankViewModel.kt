package com.xysss.whatever.profile.rank.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseViewModel
import com.xysss.model.model.Rank
import com.xysss.model.model.RankData
import com.xysss.whatever.profile.rank.RankRepository
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:33
 */

@ActivityScoped
class RankViewModel @ViewModelInject constructor(private val rankRepository: RankRepository) :
    BaseViewModel<RankData, Rank, Int>() {

    override fun getData(page: Int): LiveData<Result<RankData>> {
        return rankRepository.getRankList(page)
    }

}