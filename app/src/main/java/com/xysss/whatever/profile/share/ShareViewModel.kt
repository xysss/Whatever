package com.xysss.whatever.profile.share

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xysss.model.room.entity.Article
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:34
 */

@ActivityScoped
class ShareViewModel @ViewModelInject constructor(private val shareRepository: ShareRepository) :
    ViewModel() {

    val articleList = ArrayList<Article>()

    private val pageLiveData = MutableLiveData<Int>()

    private val pageAndCidLiveData = MutableLiveData<ShareArticle>()

    val articleLiveData = Transformations.switchMap(pageLiveData) { page ->
        shareRepository.getMyShareList(page)
    }

    val articleAndCidLiveData = Transformations.switchMap(pageAndCidLiveData) { page ->
        shareRepository.getShareList(page.cid, page.page)
    }

    fun getArticleList(page: Int) {
        pageLiveData.value = page
    }

    fun getArticleList(cid: Int, page: Int) {
        pageAndCidLiveData.value = ShareArticle(cid, page)
    }


}

data class ShareArticle(var cid: Int, var page: Int)