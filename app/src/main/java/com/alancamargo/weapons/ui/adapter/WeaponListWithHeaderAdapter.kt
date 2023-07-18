package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.databinding.ItemListWithHeaderBinding
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.core.resources.ResourcesHelper
import com.alancamargo.weapons.ui.viewholder.WeaponListWithHeaderViewHolder

class WeaponListWithHeaderAdapter(
    private val onItemClickListener: OnItemClickListener,
    private val imageLoader: ImageLoader,
    private val resourcesHelper: com.alancamargo.weapons.core.resources.ResourcesHelper
) : RecyclerView.Adapter<WeaponListWithHeaderViewHolder>() {

    private var data = emptyMap<com.alancamargo.weapons.common.ui.UiWeaponListHeader?, List<com.alancamargo.weapons.common.ui.UiWeapon>>()

    private val dataAsList by lazy {
        data.entries.toList().sortedBy { it.key?.text }
    }

    fun setData(data: Map<com.alancamargo.weapons.common.ui.UiWeaponListHeader?, List<com.alancamargo.weapons.common.ui.UiWeapon>>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeaponListWithHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListWithHeaderBinding.inflate(inflater, parent, false)
        return WeaponListWithHeaderViewHolder(imageLoader, binding, resourcesHelper).apply {
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