package cn.tabidachinokaze.weather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class WeatherApplication : Application() {
    companion object {
        const val KEY = "dc132fd82f0276fe01dd1e82d3bdf2fc"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}