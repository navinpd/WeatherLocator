package com.navin.downloadfiletest.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.navin.downloadfiletest.R

class SelectableViewAdapter(private var cities: MutableList<String>) :
    RecyclerView.Adapter<SelectableViewAdapter.CityViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun getItemCount() = cities.size

    companion object {
        val TAG = "SelectableViewAdapter"
    }

    fun updateCityList(newCities: MutableList<String>) {
        cities.clear()
        cities.addAll(newCities)
        Log.d(TAG, "$cities")
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        onClickListener = itemClickListener
    }


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val selectableText: TextView = view.findViewById(R.id.selectable_text)

        fun bind(city: String) {
            itemView.tag = city

            itemView.setOnClickListener(onClickListener)
            selectableText.text = city
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_selectable_text, parent, false)
    )


}