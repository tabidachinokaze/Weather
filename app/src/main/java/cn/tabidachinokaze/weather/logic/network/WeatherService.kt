package cn.tabidachinokaze.weather.logic.network

import cn.tabidachinokaze.weather.WeatherApplication
import cn.tabidachinokaze.weather.logic.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    //    https://api.caiyunapp.com/v2.5/UPwYOaYMDjaOSFV1/weather.json?adcode=451002
//    https://api.caiyunapp.com/v2.5/UPwYOaYMDjaOSFV1/139.7690,35.6804/weather.json?lang=ja
    @GET("v2.5/${WeatherApplication.TOKEN}/{location}/weather.json")
    fun getWeather(@Path("location") location: String): Call<Weather>

    @GET("v2.5/${WeatherApplication.TOKEN}/location/weather.json")
    fun getWeather(
        @Path("location") location: String,
        @Query("lang") lang: String
    ): Call<Weather>
}