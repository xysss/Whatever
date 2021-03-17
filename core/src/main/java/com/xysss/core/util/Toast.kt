package com.xysss.core.util

import androidx.annotation.StringRes
import com.blankj.utilcode.util.ToastUtils

/**
 * Author:bysd-2
 * Time:2021/3/1710:17
 */
fun showToast(msg: String) {
    ToastUtils.showShort(msg)
}

fun showToast(@StringRes msg: Int) {
    ToastUtils.showShort(msg)
}

fun showLongToast(msg: String) {
    ToastUtils.showLong(msg)
}

fun showLongToast(@StringRes msg: Int) {
    ToastUtils.showLong(msg)
}