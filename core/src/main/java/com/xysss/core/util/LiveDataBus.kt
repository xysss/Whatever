package com.xysss.core.util

import androidx.lifecycle.MutableLiveData

/**
 * Author:bysd-2
 * Time:2021/3/1710:15
 * 描述：LiveDataBus 事件总线 用于接受需要更新数据的黏性事件
 */
class LiveDataBus private constructor() {
    private val bus: MutableMap<String, MutableLiveData<Any>>

    private object SingletonHolder {
        val DATA_BUS = LiveDataBus()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getChannel(target: String, type: Class<T>?): MutableLiveData<T> {
        if (!bus.containsKey(target)) {
            bus[target] = MutableLiveData()
        }
        return bus[target] as MutableLiveData<T>
    }

    fun getChannel(target: String): MutableLiveData<Any> {
        return getChannel(target, Any::class.java)
    }

    companion object {
        fun get(): LiveDataBus {
            return SingletonHolder.DATA_BUS
        }
    }

    init {
        bus = HashMap()
    }

}