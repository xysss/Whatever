package com.xysss.whatever.article

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch

/**
 * Author:bysd-2
 * Time:2021/3/1711:26
 */

@ActivityScoped
class ArticleViewModel @ViewModelInject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {

    fun setCollect(
        isCollection: Int,
        pageId: Int,
        originId: Int,
        collectListener: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            articleRepository.setCollect(isCollection, pageId, originId, collectListener)
        }
    }

}