package cn.tabidachinokaze.weather.logic

import androidx.lifecycle.liveData
import cn.tabidachinokaze.weather.logic.model.Assistant
import cn.tabidachinokaze.weather.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun getTips(keywords: String) = liveData(Dispatchers.IO) {
        val result = try {
            val tipsResponse = WeatherNetwork.getTips(keywords)
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
}