package com.xysss.whatever.base

import android.content.res.Configuration
import android.view.View
import com.xysss.whatever.databinding.ActivityBaseListBinding
import com.xysss.whatever.home.ArticleCollectBaseActivity

/**
 * Author:bysd-2
 * Time:2021/3/1711:17
 */

abstract class BaseListActivity : ArticleCollectBaseActivity() {

    protected lateinit var binding: ActivityBaseListBinding
    protected var page = 1

    override fun getLayoutView(): View {
        binding = ActivityBaseListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initData() {
        getDataList()
    }

    abstract fun getDataList()

    override fun initView() {
        binding.baseListToTop.setRecyclerViewLayoutManager(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        binding.baseListToTop.onRefreshListener({
            page = 1
            getDataList()
        }, {
            page++
            getDataList()
        })
    }

    abstract fun isStaggeredGrid(): Boolean

}
