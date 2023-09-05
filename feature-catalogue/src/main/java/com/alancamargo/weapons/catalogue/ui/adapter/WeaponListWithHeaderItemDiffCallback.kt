package com.alancamargo.weapons.catalogue.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader

internal class WeaponListWithHeaderItemDiffCallback
    : DiffUtil.ItemCallback<Pair<UiWeaponListHeader?, List<UiWeapon>>>() {

    override fun areItemsTheSame(
        oldItem: Pair<UiWeaponListHeader?, List<UiWeapon>>,
        newItem: Pair<UiWeaponListHeader?, List<UiWeapon>>
    ): Boolean {
        val (oldHeader, oldWeapons) = oldItem
        val (newHeader, newWeapons) = newItem
        return oldHeader == newHeader && oldWeapons.size == newWeapons.size
    }

    override fun areContentsTheSame(
        oldItem: Pair<UiWeaponListHeader?, List<UiWeapon>>,
        newItem: Pair<UiWeaponListHeader?, List<UiWeapon>>
    ): Boolean {
        return (oldItem.first?.equals(newItem) ?: false) && oldItem.second == newItem.second
    }
}
