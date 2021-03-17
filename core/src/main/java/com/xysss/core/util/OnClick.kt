package com.xysss.core.util

import android.view.View

/**
 * Author:bysd-2
 * Time:2021/3/1710:16
 */

inline fun View.setSafeListener(crossinline action: () -> Unit) {
    var lastClick = 0L
    setOnClickListener {
        val gap = System.currentTimeMillis() - lastClick
        lastClick = System.currentTimeMillis()
        if (gap < 1500) return@setOnClickListener
        action.invoke()
    }
}