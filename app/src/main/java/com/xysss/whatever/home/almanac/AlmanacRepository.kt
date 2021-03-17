package com.xysss.whatever.home.almanac

import android.app.Application
import android.net.Uri
import com.xysss.core.almanac.CalendarUtils
import com.xysss.model.room.PlayDatabase
import com.xysss.model.room.entity.Almanac
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.util.*
import javax.inject.Inject

/**
 * Author:bysd-2
 * Time:2021/3/1711:27
 */
@ActivityRetainedScoped
class AlmanacRepository @Inject constructor(application: Application) {

    private val almanacDao = PlayDatabase.getDatabase(application).almanacDao()

    suspend fun getAlmanacUri(calendar: Calendar): Uri? {
        val julianDayFromCalendar =
            CalendarUtils.getJulianDayFromCalendar(calendar)
        val almanac = almanacDao.getAlmanac(julianDayFromCalendar)
        return if (almanac?.imgUri != null) {
            Uri.parse(almanac.imgUri)
        } else {
            null
        }
    }

    suspend fun addAlmanac(calendar: Calendar, imgUri: String) {
        almanacDao.insert(
            Almanac(
                julianDay = CalendarUtils.getJulianDayFromCalendar(calendar),
                imgUri = imgUri
            )
        )
    }

}