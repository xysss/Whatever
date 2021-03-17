package com.xysss.whatever.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xysss.core.Play
import com.xysss.core.view.base.BaseRecyclerAdapter
import com.xysss.whatever.R
import com.xysss.whatever.article.ArticleActivity
import com.xysss.whatever.article.collect.CollectListActivity
import com.xysss.whatever.databinding.AdapterProfileBinding
import com.xysss.whatever.main.login.LoginActivity
import com.xysss.whatever.profile.history.BrowseHistoryActivity
import com.xysss.whatever.profile.rank.user.UserRankActivity
import com.xysss.whatever.profile.user.UserActivity

/**
 * Author:bysd-2
 * Time:2021/3/1711:20
 */

class ProfileAdapter(
    private val mContext: Context,
    private val profileItemList: ArrayList<ProfileItem>,
) : BaseRecyclerAdapter<AdapterProfileBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder<AdapterProfileBinding> {
        val binding = AdapterProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BaseRecyclerHolder(binding)
    }


    override fun onBaseBindViewHolder(position: Int, binding: AdapterProfileBinding) {
        val data = profileItemList[position]
        binding.apply {
            profileAdTvTitle.text = data.title
            profileAdIv.setImageResource(data.imgId)
            profileAdLlItem.setOnClickListener {
                toJump(data.title)
            }
        }
    }

    private fun toJump(title: String) {
        //val dataStore = DataStoreUtils
        when (title) {
            mContext.getString(R.string.mine_points) -> {
                if (Play.isLogin) {
                    UserRankActivity.actionStart(mContext)
                } else {
                    LoginActivity.actionStart(mContext)
                }
            }
            mContext.getString(R.string.my_collection) -> {
                if (Play.isLogin) {
                    CollectListActivity.actionStart(mContext)
                } else {
                    LoginActivity.actionStart(mContext)
                }
            }
            mContext.getString(R.string.mine_blog) -> {
                ArticleActivity.actionStart(
                    mContext,
                    mContext.getString(R.string.mine_blog),
                    "https://zhujiang.blog.csdn.net/"
                )
//                dataStore.apply {
//                    saveSyncBooleanData(booleanKey, true)
//                    saveSyncIntData(intKey, 34)
//                    saveSyncStringData(stringKey, "我爱你啊")
//                    saveSyncFloatData(floatKey, 23f)
//
//                    saveSyncLongData(longKey, 45L)
//                }
//                GlobalScope.launch {
//                    dataStore.apply {
//
//                        saveStringData(stringKey, "我爱你啊")
//                        saveFloatData(floatKey, 23f)
//
//                        saveLongData(longKey, 45L)
//                    }
//                }
            }
            mContext.getString(R.string.browsing_history) -> {
                BrowseHistoryActivity.actionStart(mContext)
//                Log.e("ZHUJIANG", "哈哈哈")
//                val booleanData = dataStore.readBooleanData("BooleanData")
//                Log.e("ZHUJIANG", "booleanData: $booleanData")
//                val floatData = dataStore.readFloatData("FloatData")
//                Log.e("ZHUJIANG", "floatData: $floatData")
//                val intData = dataStore.readIntData("IntData")
//                Log.e("ZHUJIANG", "intData: $intData")
//                val longData = dataStore.readLongData("LongData")
//                Log.e("ZHUJIANG", "longData: $longData")
//                val stringData = dataStore.readStringData("StringData")
//                Log.e("ZHUJIANG", "stringData: $stringData")
//                Log.e("ZHUJIANG", "哈哈哈222")
//                GlobalScope.launch {
//                    dataStore.readBooleanFlow(booleanKey).first {
//                        Log.e("ZHUJIANG", "toJump111: ${it}" )
//                        true
//                    }
//                    dataStore.readIntFlow(intKey).first {
//                        Log.e("ZHUJIANG", "toJump222: ${it}" )
//                        true
//                    }
//                }

            }
            mContext.getString(R.string.mine_nuggets) -> {
                //dataStore.clearSync()
                ArticleActivity.actionStart(
                    mContext,
                    mContext.getString(R.string.mine_nuggets),
                    "https://juejin.im/user/5c07e51de51d451de84324d5"
                )
//                GlobalScope.launch {
//                    dataStores.apply {
//                        put(booleanKey, true)
//                        put(intKey, 34)
//                        put(stringKey, "我爱你啊")
//                        put(floatKey, 23f)
//                        put(longKey, 45L)
//                    }
//                }
            }
            mContext.getString(R.string.github) -> {
//                val a = dataStores.get(booleanKey, false)
//                val b = dataStores.get(intKey, 0)
//                val c = dataStores.get(stringKey, "")
//                val d = dataStores.get(floatKey, 0f)
//                val e = dataStores.get(longKey, 0L)
//                Log.e("ZHUJIANG", "toJump: $a   $b    $c    $d     $e" )
                ArticleActivity.actionStart(
                    mContext,
                    mContext.getString(R.string.mine_github),
                    "https://github.com/zhujiang521"
                )
            }
            mContext.getString(R.string.about_me) -> {
                UserActivity.actionStart(mContext)
            }
        }
    }

    override fun getItemCount(): Int {
        return profileItemList.size
    }

}

data class ProfileItem(var title: String, var imgId: Int)