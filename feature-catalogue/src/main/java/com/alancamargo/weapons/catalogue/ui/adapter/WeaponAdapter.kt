package com.alancamargo.weapons.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.catalogue.databinding.ItemWeaponBinding
import com.alancamargo.weapons.catalogue.ui.viewholder.WeaponViewHolder

class WeaponAdapter(
    private val resourcesHelper: com.alancamargo.weapons.core.resources.ResourcesHelper,
    private val onItemClickListener: OnItemClickListener,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<WeaponViewHolder>() {

    private var data = emptyList<com.alancamargo.weapons.common.ui.UiWeapon>()

    fun setData(data: List<com.alancamargo.weapons.common.ui.UiWeapon>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeaponBinding.inflate(inflater, parent, false)
        return WeaponViewHolder(binding, resourcesHelper, imageLoader)
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