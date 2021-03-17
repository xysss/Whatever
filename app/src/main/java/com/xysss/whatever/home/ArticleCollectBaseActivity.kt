package com.xysss.whatever.home

import android.content.BroadcastReceiver
import android.util.Log
import com.xysss.core.util.LiveDataBus
import com.xysss.core.view.base.BaseActivity
import com.xysss.whatever.article.ArticleBroadCast

/**
 * Author:bysd-2
 * Time:2021/3/1711:17
 * 描述：文章收藏 BaseActivity，注册文章收藏状态改变的广播
 */

abstract class ArticleCollectBaseActivity : BaseActivity() {

    private var articleReceiver: BroadcastReceiver? = null

    override fun onResume() {
        super.onResume()
        articleReceiver =
            ArticleBroadCast.setArticleChangesReceiver(this) { initData() }
        LiveDataBus.get().getChannel(LOGIN_REFRESH, Boolean::class.java).observe(this, {
            Log.e(TAG, "Activity onResume: $it")
            if (it) initData()
        })
    }

    override fun onPause() {
        super.onPause()
        ArticleBroadCast.clearArticleChangesReceiver(this, articleReceiver)
    }

    companion object {
        private const val TAG = "ArticleCollectBaseActiv"
    }

}