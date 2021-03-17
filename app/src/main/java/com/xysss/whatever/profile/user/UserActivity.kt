package com.xysss.whatever.profile.user

import android.content.Context
import android.content.Intent
import android.view.View
import com.xysss.core.view.base.BaseActivity
import com.xysss.whatever.databinding.ActivityUserInfoBinding

/**
 * Author:bysd-2
 * Time:2021/3/1711:23
 */

class UserActivity : BaseActivity() {

    override fun getLayoutView(): View {
        return ActivityUserInfoBinding.inflate(layoutInflater).root
    }

    override fun initView() {}

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, UserActivity::class.java)
            context.startActivity(intent)
        }
    }

}
