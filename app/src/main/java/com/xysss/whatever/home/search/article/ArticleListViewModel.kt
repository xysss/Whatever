package com.xysss.whatever.home.search.article

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.xysss.core.view.base.BaseAndroidViewModel
import com.xysss.model.model.ArticleList
import com.xysss.model.room.entity.Article
import com.xysss.whatever.home.search.SearchRepository
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Author:bysd-2
 * Time:2021/3/1711:29
 */


@ActivityScoped
class ArticleListViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
) : BaseAndroidViewModel<ArticleList, Article, QueryKeyArticle>() {

    override fun getData(page: QueryKeyArticle): LiveData<Result<ArticleList>> {
        return searchRepository.getQueryArticleList(page.page, page.k)
    }

}

data class QueryKeyArticle(var page: Int, var k: String)