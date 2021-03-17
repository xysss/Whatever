package com.xysss.whatever.article

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import com.blankj.utilcode.util.NetworkUtils
import com.bumptech.glide.Glide
import com.xysss.core.Play
import com.xysss.core.util.ProgressDialogUtil
import com.xysss.core.util.getHtmlText
import com.xysss.core.util.setSafeListener
import com.xysss.core.util.showToast
import com.xysss.core.view.base.BaseRecyclerAdapter
import com.xysss.model.room.PlayDatabase
import com.xysss.model.room.entity.Article
import com.xysss.model.room.entity.HISTORY
import com.xysss.whatever.R
import com.xysss.whatever.article.collect.CollectRepository
import com.xysss.whatever.article.collect.CollectRepositoryPoint
import com.xysss.whatever.databinding.AdapterArticleBinding
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.*

/**
 * Author:bysd-2
 * Time:2021/3/1711:25
 */

class ArticleAdapter(
    private val mContext: Context,
    private val articleList: ArrayList<Article>,
    private val isShowCollect: Boolean = true,
) : BaseRecyclerAdapter<AdapterArticleBinding>() {

    private val uiScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var progressDialogUtil: ProgressDialogUtil = ProgressDialogUtil.getInstance(mContext)!!

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerHolder<AdapterArticleBinding> {
        val binding =
            AdapterArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseRecyclerHolder(binding)
    }

    private fun setCollect(
        collectRepository: CollectRepository,
        t: Article,
        articleTvCollect: ImageView
    ) {
        progressDialogUtil.progressDialogShow(
            if (t.collect) mContext.getString(R.string.bookmarking) else mContext.getString(
                R.string.unfavorite
            )
        )
        uiScope.launch {
            val articleDao = PlayDatabase.getDatabase(mContext).browseHistoryDao()
            if (!t.collect) {
                val cancelCollects = collectRepository.cancelCollects(t.id)
                if (cancelCollects.errorCode == 0) {
                    withContext(Dispatchers.Main) {
                        articleTvCollect.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                        showToast(mContext.getString(R.string.collection_cancelled_successfully))
                        articleDao.update(t)
                        progressDialogUtil.progressDialogDismiss()
                    }
                } else {
                    showToast(mContext.getString(R.string.failed_to_cancel_collection))
                    progressDialogUtil.progressDialogDismiss()
                }
            } else {
                val toCollects = collectRepository.toCollects(t.id)
                if (toCollects.errorCode == 0) {
                    withContext(Dispatchers.Main) {
                        articleTvCollect.setImageResource(R.drawable.ic_favorite_black_24dp)
                        showToast(mContext.getString(R.string.collection_successful))
                        articleDao.update(t)
                        progressDialogUtil.progressDialogDismiss()
                    }
                } else {
                    showToast(mContext.getString(R.string.collection_failed))
                    progressDialogUtil.progressDialogDismiss()
                }
            }
        }
    }

    override fun onBaseBindViewHolder(position: Int, binding: AdapterArticleBinding) {
        val data = articleList[position]
        val collectRepository = EntryPointAccessors.fromApplication(
            mContext,
            CollectRepositoryPoint::class.java
        ).collectRepository()
        binding.apply {
            if (!TextUtils.isEmpty(data.title))
                articleTvTitle.text = getHtmlText(data.title)
            articleTvChapterName.text = data.superChapterName
            articleTvAuthor.text =
                if (TextUtils.isEmpty(data.author)) data.shareUser else data.author
            articleTvTime.text = data.niceShareDate
            if (!TextUtils.isEmpty(data.envelopePic)) {
                articleIvImg.visibility = View.VISIBLE
                Glide.with(mContext).load(data.envelopePic).into(articleIvImg)
            } else {
                articleIvImg.visibility = View.GONE
            }
            articleTvTop.isVisible = data.type > 0
            articleTvNew.isVisible = data.fresh

            articleIvCollect.isVisible = isShowCollect
            if (data.collect) {
                articleIvCollect.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                articleIvCollect.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }
            articleIvCollect.setSafeListener {
                if (Play.isLogin) {
                    if (NetworkUtils.isConnected()) {
                        data.collect = !data.collect
                        setCollect(collectRepository, data, articleIvCollect)
                    } else {
                        showToast(mContext.getString(R.string.no_network))
                    }
                } else {
                    showToast(mContext.getString(R.string.not_currently_logged_in))
                }
            }
            articleLlItem.setOnClickListener {
                if (!NetworkUtils.isConnected()) {
                    showToast(mContext.getString(R.string.no_network))
                    return@setOnClickListener
                }
                ArticleActivity.actionStart(mContext, data)
                val browseHistoryDao = PlayDatabase.getDatabase(mContext).browseHistoryDao()
                uiScope.launch {
                    if (browseHistoryDao.getArticle(data.id, HISTORY) == null) {
                        data.localType = HISTORY
                        data.desc = ""
                        browseHistoryDao.insert(data)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

}
