package com.xysss.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author:bysd-2
 * Time:2021/3/1710:56
 */
@Entity(tableName = "banner_bean")
data class BannerBean(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "desc")val desc: String,
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "imagePath")val imagePath: String,
    @ColumnInfo(name = "isVisible")val isVisible: Int,
    @ColumnInfo(name = "order")val order: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "type")val type: Int,
    @ColumnInfo(name = "url")val url: String,
    @ColumnInfo(name = "file_path")var filePath: String
)