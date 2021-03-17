package com.xysss.core.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * Author:bysd-2
 * Time:2021/3/1710:15
 */
object IntentShareUtils {

    @SuppressLint("QueryPermissionsNeeded")
    fun shareFile(activity: Activity, uri: Uri?, title: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, uri)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(Intent.createChooser(intent, title))
        }
    }

}