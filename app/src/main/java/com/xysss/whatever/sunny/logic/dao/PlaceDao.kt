package com.xysss.whatever.sunny.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.xysss.whatever.sunny.logic.model.Place
import com.xysss.whatever.App


object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        App.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}