package com.alancamargo.weapons.home.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.weapons.home.ui.model.WeaponQueryType

internal class WeaponQueryTypeDiffCallback : DiffUtil.ItemCallback<WeaponQueryType>() {

    override fun areItemsTheSame(oldItem: WeaponQueryType, newItem: WeaponQueryType): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeaponQueryType, newItem: WeaponQueryType): Boolean {
        return oldItem == newItem
    }
}
