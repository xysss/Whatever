package com.xysss.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author:bysd-2
 * Time:2021/3/1710:56
 */
@Entity(tableName = "almanac")
data class Almanac(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "julian_day") val julianDay: Int = 0,
    @ColumnInfo(name = "img_uri") val imgUri: String,
)