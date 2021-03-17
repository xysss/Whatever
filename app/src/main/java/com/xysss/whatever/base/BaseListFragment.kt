package com.xysss.whatever.base

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xysss.whatever.article.ArticleAdapter
import com.xysss.whatever.databinding.FragmentBaseListBinding
import com.xysss.whatever.home.ArticleCollectBaseFragment

/**
 * Author:bysd-2
 * Time:2021/3/1711:26
 */

abstract class BaseListFragment : ArticleCollectBaseFragment() {

    protected var binding: FragmentBaseListBinding? = null

    protected lateinit var articleAdapter: ArticleAdapter
    protected var page = 1

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View {
        binding = FragmentBaseListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun initView() {
        binding?.apply {
            baseFragmentToTop.setRecyclerViewLayoutManager(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            baseFragmentToTop.setAdapter(articleAdapter)
            baseFragmentToTop.onRefreshListener({
                page = 1
                refreshData()
            }, {
                page++
                refreshData()
            })
        }
    }

}
