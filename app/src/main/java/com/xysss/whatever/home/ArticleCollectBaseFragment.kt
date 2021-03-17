package com.xysss.whatever.home

import android.content.BroadcastReceiver
import android.util.Log
import com.xysss.core.util.LiveDataBus
import com.xysss.core.view.base.BaseFragment
import com.xysss.whatever.article.ArticleBroadCast

/**
 * Author:bysd-2
 * Time:2021/3/1711:29
 * 描述：文章收藏 BaseFragment，注册文章收藏状态改变的广播
 */
abstract class ArticleCollectBaseFragment : BaseFragment() {

    private var articleReceiver: BroadcastReceiver? = null

    override fun onResume() {
        super.onResume()
        articleReceiver =
            ArticleBroadCast.setArticleChangesReceiver(requireActivity()) { refreshData() }
        LiveDataBus.get().getChannel(LOGIN_REFRESH, Boolean::class.java).observe(this, {
            Log.e(TAG, "Fragment onResume: $it")
            if (it) refreshData()
        })
    }

    abstract fun refreshData()

    override fun onPause() {
        super.onPause()
        ArticleBroadCast.clearArticleChangesReceiver(requireActivity(), articleReceiver)
    }

    companion object {
        private const val TAG = "ArticleCollectBaseFragm"
    }

}

const val LOGIN_REFRESH = "LOGIN_REFRESH"