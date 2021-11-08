package cn.tabidachinokaze.weather.logic

import androidx.lifecycle.liveData
import cn.tabidachinokaze.weather.logic.dao.PlaceDao
import cn.tabidachinokaze.weather.logic.model.Assistant
import cn.tabidachinokaze.weather.logic.model.Weather
import cn.tabidachinokaze.weather.logic.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

object Repository {
    fun getTips(keywords: String) = liveData(Dispatchers.IO) {
        val result = try {
            val tipsResponse = Network.getTips(keywords)
            if (tipsResponse.status != "0") {
                val tips = tipsResponse.tips
                Result.success(tips)
            } else {
                Result.failure(RuntimeException("response status is ${tipsResponse.info}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Assistant.Tips>>(e)
        }
        emit(result)
    }

    fun refreshWeather(location: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                val deferredWeather = async {
                    Network.getWeather(location)
                }
                val weatherResponse = deferredWeather.await()
                if (weatherResponse.status == "ok") {
                    Result.success(weatherResponse)
                } else {
                    Result.failure(
                        RuntimeException(
                            "weather response status is ${weatherResponse.status}"
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }
        emit(result)
    }

    fun savePlace(place: Assistant.Tips) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}