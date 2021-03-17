package com.xysss.whatever.profile

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xysss.core.Play
import com.xysss.core.Play.logout
import com.xysss.core.view.base.BaseFragment
import com.xysss.whatever.R
import com.xysss.whatever.article.ArticleBroadCast
import com.xysss.whatever.databinding.FragmentProfileBinding
import com.xysss.whatever.main.login.AccountRepository
import com.xysss.whatever.main.login.LoginActivity
import com.xysss.whatever.profile.rank.list.RankActivity
import com.xysss.whatever.profile.share.ShareActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:23
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment(), View.OnClickListener {

    private var binding: FragmentProfileBinding? = null

    @Inject
    lateinit var accountRepository: AccountRepository

    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var nameArray: Array<String>
    private var profileItemList = ArrayList<ProfileItem>()
    private val imageArray = arrayOf(
        R.drawable.ic_message_black_24dp,
        R.drawable.ic_collections_black_24dp,
        R.drawable.ic_account_blog_black_24dp,
        R.drawable.ic_baseline_history_24,
        R.drawable.ic_bug_report_black_24dp,
        R.drawable.ic_github_black_24dp,
        R.drawable.ic_account_circle_black_24dp
    )

    override fun getLayoutView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun initView() {
        binding?.apply {
            profileTitleBar.setRightImage(R.drawable.btn_right_right_bg)
            profileTitleBar.setRightImgOnClickListener {
                RankActivity.actionStart(requireContext())
            }
            profileIvHead.setOnClickListener(this@ProfileFragment)
            profileTvName.setOnClickListener(this@ProfileFragment)
            profileTvRank.setOnClickListener(this@ProfileFragment)
            profileBtnLogout.setOnClickListener(this@ProfileFragment)
            profileRv.layoutManager = LinearLayoutManager(context)
            profileAdapter = ProfileAdapter(requireContext(), profileItemList)
            profileRv.adapter = profileAdapter
            if (Play.isLogin) {
                profileIvHead.setBackgroundResource(R.drawable.ic_head)
                profileTvName.text = Play.nickName
                profileTvRank.text = Play.username
                profileBtnLogout.visibility = View.VISIBLE
            } else {
                clearInfo()
            }
        }
    }

    private fun clearInfo() {
        binding?.apply {
            profileBtnLogout.visibility = View.GONE
            profileIvHead.setBackgroundResource(R.drawable.img_nomal_head)
            profileTvName.text = getString(R.string.no_login)
            profileTvRank.text = getString(R.string.click_login)
        }
        ArticleBroadCast.sendArticleChangesReceiver(requireContext())
    }

    override fun initData() {
        if (profileItemList.size == 0) {
            nameArray = arrayOf(
                getString(R.string.mine_points),
                getString(R.string.my_collection),
                getString(R.string.mine_blog),
                getString(R.string.browsing_history),
                getString(R.string.mine_nuggets),
                getString(R.string.github),
                getString(R.string.about_me)
            )
            for (index in nameArray.indices) {
                profileItemList.add(
                    ProfileItem(nameArray[index], imageArray[index])
                )
            }
            profileAdapter.notifyDataSetChanged()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.profileIvHead, R.id.profileTvName, R.id.profileTvRank -> personalInformation()
            R.id.profileBtnLogout -> setLogout()
        }
    }

    private fun setLogout() {
        AlertDialog.Builder(context).setTitle(getString(R.string.log_out))
            .setMessage(getString(R.string.sure_log_out))
            .setNegativeButton(
                getString(R.string.cancel)
            ) { dialog, _ -> dialog?.dismiss() }
            .setPositiveButton(getString(R.string.sure)) { dialog, _ ->
                dialog?.dismiss()
                clearInfo()
                logout()
                accountRepository.getLogout()
            }.show()
    }

    private fun personalInformation() {
        if (!Play.isLogin) {
            LoginActivity.actionStart(requireContext())
        } else {
            ShareActivity.actionStart(requireContext(), true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

}
