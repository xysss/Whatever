package com.xysss.whatever.article

import android.app.Application
import com.xysss.core.Play
import com.xysss.core.util.showToast
import com.xysss.whatever.R
import com.xysss.whatever.article.collect.CollectRepositoryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:25
 */
@ActivityRetainedScoped
class ArticleRepository @Inject constructor(val application: Application) {

    suspend fun setCollect(
        isCollection: Int,
        pageId: Int,
        originId: Int,
        collectListener: (Boolean) -> Unit
    ) {

        if (!Play.isLogin) {
            showToast(R.string.not_currently_logged_in)
            return
        }

        if (isCollection == -1 || pageId == -1) {
            showToast(R.string.page_is_not_collection)
            return
        }
        val collectRepository = EntryPointAccessors.fromApplication(
            application,
            CollectRepositoryPoint::class.java
        ).collectRepository()
        withContext(Dispatchers.IO) {
            if (isCollection == 1) {
                val cancelCollects =
                    collectRepository.cancelCollects(if (originId != -1) originId else pageId)
                if (cancelCollects.errorCode == 0) {
                    showToast(R.string.collection_cancelled_successfully)
                    ArticleBroadCast.sendArticleChangesReceiver(application)
                    collectListener.invoke(false)
                } else {
                    showToast(R.string.failed_to_cancel_collection)
                }
            } else {
                val toCollects = collectRepository.toCollects(pageId)
                if (toCollects.errorCode == 0) {
                    showToast(R.string.collection_successful)
                    ArticleBroadCast.sendArticleChangesReceiver(application)
                    collectListener.invoke(true)
                } else {
                    showToast(R.string.collection_failed)
                }

            }
        }

    }

}