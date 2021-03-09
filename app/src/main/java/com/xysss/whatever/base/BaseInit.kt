package com.sin.kotlintest.base

import android.view.LayoutInflater
import android.view.View

interface BaseInit {
    fun initData();
    fun initView();
}

interface BaseActivityInit : BaseInit {
    fun getLayoutView():View

}

interface BaseFragmentInit:BaseInit{
   // fun getLayoutView(inflater:LayoutInflater,)
}