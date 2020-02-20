package com.navin.downloadfiletest.ui.base

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.navin.downloadfiletest.R

abstract class BaseItemAdapter {


    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(city: String) {
            itemView.tag = city

        }
    }
}