package com.alancamargo.weapons.catalogue.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader

internal class WeaponListWithHeaderItemDiffCallback
    : DiffUtil.ItemCallback<Map.Entry<UiWeaponListHeader?, List<UiWeapon>>>() {

    override fun areItemsTheSame(
        oldItem: Map.Entry<UiWeaponListHeader?, List<UiWeapon>>,
        newItem: Map.Entry<UiWeaponListHeader?, List<UiWeapon>>
    ): Boolean {
        return oldItem.key == newItem.key && oldItem.value.size == newItem.value.size
    }

    override fun areContentsTheSame(
        oldItem: Map.Entry<UiWeaponListHeader?, List<UiWeapon>>,
        newItem: Map.Entry<UiWeaponListHeader?, List<UiWeapon>>
    ): Boolean {
        return (oldItem.key?.equals(newItem) ?: false) && oldItem.value == newItem.value
    }
}
