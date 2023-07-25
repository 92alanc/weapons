package com.alancamargo.weapons.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.weapons.catalogue.databinding.ItemWeaponBinding
import com.alancamargo.weapons.catalogue.ui.viewholder.WeaponViewHolder
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.resources.ResourcesHelper

internal class WeaponAdapter(
    private val resourcesHelper: ResourcesHelper,
    private val onItemClick: (UiWeapon) -> Unit
) : ListAdapter<UiWeapon, WeaponViewHolder>(WeaponDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeaponBinding.inflate(inflater, parent, false)
        return WeaponViewHolder(binding, resourcesHelper, onItemClick)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon = getItem(position)
        holder.bindTo(weapon)
    }
}
