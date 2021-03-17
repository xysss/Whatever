package com.xysss.model.room.dao

import androidx.room.*
import com.xysss.model.room.entity.ProjectClassify

/**
 * Author:bysd-2
 * Time:2021/3/1710:55
 */
@Dao
interface ProjectClassifyDao {

    @Query("SELECT * FROM project_classify where order_classify>144999 and order_classify<145050")
    suspend fun getAllProject(): List<ProjectClassify>

    @Query("SELECT * FROM project_classify where order_classify>189999 and order_classify<190020")
    suspend fun getAllOfficial(): List<ProjectClassify>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(projectClassifyList: List<ProjectClassify>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(projectClassify: ProjectClassify)

    @Delete
    suspend fun delete(projectClassify: ProjectClassify): Int

    @Delete
    suspend fun deleteList(projectClassifyList: List<ProjectClassify>): Int

    @Query("DELETE FROM project_classify")
    suspend fun deleteAll()
}
