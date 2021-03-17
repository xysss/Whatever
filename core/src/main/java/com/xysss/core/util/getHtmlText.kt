package com.xysss.core.util

import android.text.Html

/**
 * Author:bysd-2
 * Time:2021/3/1710:15
 */
fun getHtmlText(text: String): String {
    return if (AndroidVersion.hasNougat()) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        text
    }
}