package com.xysss.whatever.home

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.NetworkUtils
import com.bumptech.glide.Glide
import com.xysss.core.util.showToast
import com.xysss.model.room.entity.BannerBean
import com.xysss.whatever.R
import com.xysss.whatever.article.ArticleActivity
import com.youth.banner.adapter.BannerAdapter

/**
 * Author:bysd-2
 * Time:2021/3/1711:30
 */
open class ImageAdapter(private val mContext: Context, mData: List<BannerBean>) :
    BannerAdapter<BannerBean?, ImageAdapter.BannerViewHolder?>(mData) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewHolder {
        val imageView = ImageView(parent.context).apply {
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        return BannerViewHolder(imageView)
    }

    class BannerViewHolder(view: ImageView) :
        RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view
    }

    override fun onBindView(
        holder: BannerViewHolder?,
        data: BannerBean?,
        position: Int,
        size: Int
    ) {
        Glide.with(mContext).load(if (data?.filePath == null) data?.imagePath else data.filePath)
            .into(holder!!.imageView)
        holder.imageView.setOnClickListener {
            if (!NetworkUtils.isConnected()) {
                showToast(mContext.getString(R.string.no_network))
                return@setOnClickListener
            }
            ArticleActivity.actionStart(mContext, data!!.title, data.url)
        }
    }
}