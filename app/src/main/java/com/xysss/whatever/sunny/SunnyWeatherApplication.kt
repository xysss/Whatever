package com.xysss.whatever.sunny

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    //companion 静态  object 单例
    companion object {
        const val TOKEN = "BhF86VEauO56lSCo" // 填入你申请到的令牌值 xys
        @SuppressLint("StaticFieldLeak")  //标注忽略指定的警告
        lateinit var context: Context  //lateinit 延迟加载
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}