package com.alancamargo.weapons.catalogue.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.weapons.common.ui.UiWeapon

internal class WeaponDiffCallback : DiffUtil.ItemCallback<UiWeapon>() {

    override fun areItemsTheSame(oldItem: UiWeapon, newItem: UiWeapon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiWeapon, newItem: UiWeapon): Boolean {
        return oldItem == newItem
    }
}
