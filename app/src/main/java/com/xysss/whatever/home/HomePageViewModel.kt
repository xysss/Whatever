package com.xysss.whatever.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.xysss.model.pojo.QueryHomeArticle
import com.xysss.model.room.entity.Article
import com.xysss.model.room.entity.BannerBean
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:30
 */
@FragmentScoped
class HomePageViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val pageLiveData = MutableLiveData<QueryHomeArticle>()

    val bannerList = ArrayList<BannerBean>()

    val bannerList2 = ArrayList<BannerBean>()

    val articleList = ArrayList<Article>()

    val articleLiveData = Transformations.switchMap(pageLiveData) { query ->
        homeRepository.getArticleList(query)
    }

    fun getBanner() = homeRepository.getBanner()

    fun getArticleList(page: Int, isRefresh: Boolean) {
        pageLiveData.value = QueryHomeArticle(page, isRefresh)
    }

}