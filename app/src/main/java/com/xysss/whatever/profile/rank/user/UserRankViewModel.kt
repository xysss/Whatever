package com.xysss.whatever.profile.rank.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseViewModel
import com.xysss.model.model.RankList
import com.xysss.model.model.Ranks
import com.xysss.whatever.profile.rank.RankRepository
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:34
 */

@ActivityScoped
class UserRankViewModel @ViewModelInject constructor(private val rankRepository: RankRepository) :
    BaseViewModel<RankList, Ranks, Int>() {

    override fun getData(page: Int): LiveData<Result<RankList>> {
        return rankRepository.getUserRank(page)
    }

}