package com.xysss.whatever.article

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Author:bysd-2
 * Time:2021/3/1711:26
 */

object ArticleUtils {

    fun copyToClipboard(context: Context, text: String?) {
        val systemService: ClipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        systemService.setPrimaryClip(ClipData.newPlainText("text", text))
    }

    fun jumpBrowser(context: Context, url: String) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    fun shareUrl(context: Context, url: String, name: String) {
        val textIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
        }
        context.startActivity(Intent.createChooser(textIntent, name))
    }

}