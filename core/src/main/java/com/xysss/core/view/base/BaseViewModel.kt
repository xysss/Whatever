package com.xysss.core.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * Author:bysd-2
 * Time:2021/3/1710:27
 */
abstract class BaseViewModel<BaseData, Data, Key> : ViewModel() {

    val dataList = ArrayList<Data>()

    private val pageLiveData = MutableLiveData<Key>()

    val dataLiveData = Transformations.switchMap(pageLiveData) { page ->
        getData(page)
    }

    abstract fun getData(page: Key): LiveData<Result<BaseData>>

    fun getDataList(page: Key) {
        pageLiveData.value = page
    }

}