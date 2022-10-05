package com.xy.wathertest.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.xy.wathertest.MyApplication
import com.xy.wathertest.logic.model.Place

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

    private fun sharedPreferences() =
        MyApplication.context.getSharedPreferences("weather_test", Context.MODE_PRIVATE)

    fun isPlaceSaved() = sharedPreferences().contains("place")
}