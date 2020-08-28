package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.UiWeaponListHeader
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.viewholder.WeaponListWithHeaderViewHolder

class WeaponListWithHeaderAdapter(
    private val onItemClickListener: OnItemClickListener,
    private val imageLoader: ImageLoader,
    private val resourcesHelper: ResourcesHelper
) : RecyclerView.Adapter<WeaponListWithHeaderViewHolder>() {

    private var data = emptyMap<UiWeaponListHeader?, List<UiWeapon>>()

    private val dataAsList by lazy {
        data.entries.toList().sortedBy { it.key?.text }
    }

    fun setData(data: Map<UiWeaponListHeader?, List<UiWeapon>>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeaponListWithHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_list_with_header, parent, false)
        return WeaponListWithHeaderViewHolder(itemView, imageLoader, resourcesHelper).apply {
            setOnItemClickListener(onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: WeaponListWithHeaderViewHolder, position: Int) {
        val entry = dataAsList[position]
        holder.bindTo(entry)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = dataAsList[position].value.first().id

}