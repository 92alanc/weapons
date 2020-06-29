package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.viewholder.CountryViewHolder

class CountryAdapter(
    private val resourcesHelper: ResourcesHelper,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CountryViewHolder>() {

    private var data = emptyList<UiCountry>()

    fun setData(data: List<UiCountry>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(itemView, resourcesHelper)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = data[position]
        holder.bindTo(country)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(country)
        }
    }

    override fun getItemCount(): Int = data.size

    interface OnItemClickListener {
        fun onItemClick(country: UiCountry)
    }

}