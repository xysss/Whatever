package com.xysss.whatever.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author:bysd-2
 * Time:2021/3/1711:32
 */
class MainViewModel : ViewModel() {

    private val pageLiveData = MutableLiveData<Int>()

    fun setPage(page: Int) {
        pageLiveData.value = page
    }

    fun getPage():Int? {
        return pageLiveData.value
    }

}