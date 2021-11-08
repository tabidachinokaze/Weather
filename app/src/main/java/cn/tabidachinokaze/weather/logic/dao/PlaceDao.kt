package cn.tabidachinokaze.weather.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import cn.tabidachinokaze.weather.WeatherApplication
import cn.tabidachinokaze.weather.logic.model.Assistant
import com.google.gson.Gson

object PlaceDao {
    fun savePlace(place: Assistant.Tips) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }
    fun getSavedPlace(): Assistant.Tips {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Assistant.Tips::class.java)
    }
    fun isPlaceSaved() = sharedPreferences().contains("place")
    private fun sharedPreferences(): SharedPreferences =WeatherApplication.context.getSharedPreferences("weather", Context.MODE_PRIVATE)
}