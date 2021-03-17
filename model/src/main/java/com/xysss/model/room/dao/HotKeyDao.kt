package com.xysss.model.room.dao

import androidx.room.*
import com.xysss.model.room.entity.HotKey

/**
 * Author:bysd-2
 * Time:2021/3/1710:55
 */
@Dao
interface HotKeyDao {

    @Query("SELECT * FROM hot_key order by uid desc")
    suspend fun getHotKeyList(): List<HotKey>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(HotKeyList: List<HotKey>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(HotKey: HotKey)

    @Update
    suspend fun update(HotKey: HotKey): Int

    @Delete
    suspend fun delete(HotKey: HotKey): Int

    @Delete
    suspend fun deleteList(HotKeyList: List<HotKey>): Int

    @Query("DELETE FROM hot_key")
    suspend fun deleteAll()
}
