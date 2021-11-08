package cn.tabidachinokaze.weather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object Network {
    private val assistantService = AssistantServiceCreator.create<AssistantService>()
    suspend fun getTips(keywords: String) = assistantService.getTips(keywords).await()
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    private val weatherService = WeatherServiceCreator.create(WeatherService::class.java)
    suspend fun getWeather(location: String) = weatherService.getWeather(location).await()
    suspend fun getWeather(location: String, lang: String) =
        weatherService.getWeather(location, lang).await()
}
