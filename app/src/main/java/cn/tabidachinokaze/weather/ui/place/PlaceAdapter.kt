package cn.tabidachinokaze.weather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cn.tabidachinokaze.weather.R
import cn.tabidachinokaze.weather.logic.model.Assistant

class PlaceAdapter(private val fragment: Fragment, private val tips: List<Assistant.Tips>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeDistrict: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tip = tips[position]
        holder.placeName.text = tip.name
        holder.placeDistrict.text = tip.district
    }

    override fun getItemCount(): Int {
        return tips.size
    }
}