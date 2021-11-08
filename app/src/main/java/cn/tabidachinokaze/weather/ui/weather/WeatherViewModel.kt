package cn.tabidachinokaze.weather.ui.weather


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.tabidachinokaze.weather.logic.Repository
import cn.tabidachinokaze.weather.logic.model.Location
import kotlin.math.ln

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<String>()
    var location = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location)
    }
    fun refreshWeather(location: String) {
        locationLiveData.value = location
    }
}