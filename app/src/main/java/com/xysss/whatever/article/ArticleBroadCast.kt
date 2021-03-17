package com.xysss.whatever.article

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * Author:bysd-2
 * Time:2021/3/1711:25
 */
object ArticleBroadCast {

    const val COLLECT_RECEIVER = "com.zj.play.COLLECT"

    fun sendArticleChangesReceiver(context: Context) {
        val intent = Intent(COLLECT_RECEIVER)
        intent.setPackage(context.packageName)
        context.sendBroadcast(intent)
    }

    fun setArticleChangesReceiver(c: Activity, block: () -> Unit): BroadcastReceiver? {
        val filter = IntentFilter()
        filter.addAction(COLLECT_RECEIVER)
        val r = ArticleBroadcastReceiver(block)
        //LocalBroadcastManager.getInstance(c).registerReceiver(r, filter)
        c.registerReceiver(r, filter)
        return r
    }

    fun clearArticleChangesReceiver(c: Activity, r: BroadcastReceiver?) {
        if (r != null)
            LocalBroadcastManager.getInstance(c).unregisterReceiver(r)
    }

}

private class ArticleBroadcastReceiver(val block: () -> Unit) :
    BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ArticleBroadCast.COLLECT_RECEIVER) {
            block.invoke()
        }
    }
}