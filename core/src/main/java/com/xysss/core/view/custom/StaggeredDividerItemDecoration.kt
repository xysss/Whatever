package com.xysss.core.view.custom

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Author:bysd-2
 * Time:2021/3/1710:31
 */
class StaggeredDividerItemDecoration(private val context: Context, private val interval: Int = 5) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        // 获取item在span中的下标
        val spanIndex = params.spanIndex
        val interval = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            interval.toFloat(), context.resources.displayMetrics
        ).toInt()
        // 中间间隔
        if (spanIndex % 2 == 0) {
            outRect.left = 0
        } else {
            // item为奇数位，设置其左间隔为5dp
            outRect.left = interval
        }
        // 下方间隔
        outRect.bottom = interval
    }
}