package cn.tabidachinokaze.weather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.tabidachinokaze.weather.logic.Repository
import cn.tabidachinokaze.weather.logic.model.Assistant

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val tips = ArrayList<Assistant.Tips>()
    val tipsLiveData = Transformations.switchMap(searchLiveData) { keywords ->
        Repository.getTips(keywords)
    }

    fun getTips(keywords: String) {
        searchLiveData.value = keywords
    }
}