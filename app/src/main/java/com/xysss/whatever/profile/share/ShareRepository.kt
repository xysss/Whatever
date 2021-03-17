package com.xysss.whatever.profile.share

import com.xysss.network.base.PlayAndroidNetwork
import com.xysss.whatever.main.login.fires
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:34
 */

@ActivityRetainedScoped
class ShareRepository @Inject constructor() {

    fun getMyShareList(page: Int) = fires { PlayAndroidNetwork.getMyShareList(page) }

    fun getShareList(cid: Int, page: Int) = fires { PlayAndroidNetwork.getShareList(cid, page) }

    fun deleteMyArticle(cid: Int) = fires { PlayAndroidNetwork.deleteMyArticle(cid) }

    fun shareArticle(title: String, link: String) =
        fires { PlayAndroidNetwork.shareArticle(title, link) }

}