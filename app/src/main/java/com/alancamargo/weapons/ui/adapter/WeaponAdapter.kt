package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.viewholder.WeaponViewHolder

class WeaponAdapter(
    private val resourcesHelper: ResourcesHelper,
    private val onItemClickListener: OnItemClickListener,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<WeaponViewHolder>() {

    private var data = emptyList<UiWeapon>()

    fun setData(data: List<UiWeapon>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_weapon, parent, false)
        return WeaponViewHolder(itemView, resourcesHelper, imageLoader)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon = data[position]
        with(holder) {
            bindTo(weapon)
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(weapon)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = data[position].id

    override fun getItemViewType(position: Int): Int = position

}