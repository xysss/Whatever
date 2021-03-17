package com.xysss.whatever.project

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.xysss.core.view.base.BaseFragment

/**
 * Author:bysd-2
 * Time:2021/3/1711:23
 */

abstract class BaseTabFragment : BaseFragment(), ViewPager.OnPageChangeListener,
    TabLayout.OnTabSelectedListener {

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onPageSelected(position: Int) {
        onTabPageSelected(position)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab != null && tab.position > 0)
            onTabPageSelected(tab.position)
    }

    abstract fun onTabPageSelected(position: Int)

}