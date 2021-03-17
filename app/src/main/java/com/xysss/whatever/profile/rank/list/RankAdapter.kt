package com.xysss.whatever.profile.rank.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xysss.core.view.base.BaseRecyclerAdapter
import com.xysss.model.model.Rank
import com.xysss.whatever.R
import com.xysss.whatever.databinding.AdapterRankBinding
import com.xysss.whatever.profile.share.ShareActivity
import java.util.ArrayList

/**
 * Author:bysd-2
 * Time:2021/3/1711:33
 */

class RankAdapter(
    private val mContext: Context,
    private val rankList: ArrayList<Rank>,
) : BaseRecyclerAdapter<AdapterRankBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerHolder<AdapterRankBinding> {
        val binding = AdapterRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseRecyclerHolder(binding)
    }

    override fun getItemCount(): Int {
        return rankList.size
    }

    override fun onBaseBindViewHolder(position: Int, binding: AdapterRankBinding) {
        val data = rankList[position]
        binding.apply {
            rankAdTvUsername.text = data.username
            rankAdTvRank.text = mContext.getString(R.string.ranking, data.rank)
            rankAdTvCoinCount.text = mContext.getString(R.string.coin, data.coinCount)
            rankAdTvLevel.text = mContext.getString(R.string.lever, data.level)
            rankAdRlItem.setOnClickListener {
                ShareActivity.actionStart(mContext, false, data.userId)
            }
        }
    }

}