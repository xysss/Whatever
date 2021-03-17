package com.xysss.core.util

import java.util.*

/**
 * Author:bysd-2
 * Time:2021/3/1710:15
 */
fun isZhLanguage(): Boolean {
    return Locale.getDefault().language == "zh"
}