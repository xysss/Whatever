package com.xysss.model.room.dao

import androidx.room.*
import com.xysss.model.room.entity.Almanac

/**
 * Author:bysd-2
 * Time:2021/3/1710:53
 */
@Dao
interface AlmanacDao {

    @Query("SELECT * FROM almanac order by uid desc")
    suspend fun getAlmanacList(): List<Almanac>

    @Query("SELECT * FROM almanac where julian_day = :julianDay")
    suspend fun getAlmanac(julianDay: Int): Almanac?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(almanacList: List<Almanac>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(almanac: Almanac)

    @Update
    suspend fun update(almanac: Almanac): Int

    @Delete
    suspend fun delete(almanac: Almanac): Int

    @Delete
    suspend fun deleteList(almanacList: List<Almanac>): Int

    @Query("DELETE FROM almanac")
    suspend fun deleteAll()
}
