package com.alancamargo.weapons.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.weapons.catalogue.databinding.ItemListWithHeaderBinding
import com.alancamargo.weapons.catalogue.ui.viewholder.WeaponListWithHeaderViewHolder
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.core.resources.ResourcesHelper

internal class WeaponListWithHeaderAdapter(
    private val resourcesHelper: ResourcesHelper,
    private val onItemClick: (UiWeapon) -> Unit
) : ListAdapter<Map.Entry<UiWeaponListHeader?, List<UiWeapon>>, WeaponListWithHeaderViewHolder>(
    WeaponListWithHeaderItemDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeaponListWithHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListWithHeaderBinding.inflate(inflater, parent, false)
        return WeaponListWithHeaderViewHolder(binding, resourcesHelper, onItemClick)
    }

    override fun onBindViewHolder(holder: WeaponListWithHeaderViewHolder, position: Int) {
        val entry = getItem(position)
        holder.bindTo(entry)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
