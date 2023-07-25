package com.alancamargo.weapons.catalogue.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alancamargo.weapons.catalogue.databinding.ItemWeaponBinding
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.core.resources.ResourcesHelper

internal class WeaponViewHolder(
    private val binding: ItemWeaponBinding,
    private val resourcesHelper: ResourcesHelper,
    private val onItemClick: (UiWeapon) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(weapon: UiWeapon) = with(binding) {
        txtName.text = weapon.name

        val flagDrawable = weapon.country?.flagId?.let {
            resourcesHelper.getDrawable(it)
        }

        imgFlag.setImageDrawable(flagDrawable)

        if (weapon.photos.isNotEmpty()) {
            imgPhoto.load(weapon.photos.first())
        }

        root.setOnClickListener {
            onItemClick(weapon)
        }
    }
}
