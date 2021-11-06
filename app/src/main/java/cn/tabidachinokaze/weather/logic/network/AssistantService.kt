package cn.tabidachinokaze.weather.logic.network

import cn.tabidachinokaze.weather.WeatherApplication
import cn.tabidachinokaze.weather.logic.model.Assistant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AssistantService {
    @GET("v3/assistant/inputtips?key=${WeatherApplication.KEY}")
    fun getTips(@Query("keywords") keywords: String): Call<Assistant>
}