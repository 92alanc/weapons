package com.alancamargo.weapons.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.databinding.ItemWeaponBinding
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeaponViewHolder(
    private val binding: ItemWeaponBinding,
    private val resourcesHelper: ResourcesHelper,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(weapon: UiWeapon) = with(binding) {
        txtName.text = weapon.name

        val flagDrawable = weapon.country?.flagId?.let {
            resourcesHelper.getDrawable(it)
        }

        imgFlag.setImageDrawable(flagDrawable)

        if (weapon.photos.isNotEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                imgPhoto.load(imageLoader, weapon.photos.first(), progressBar, txtError)
            }
        }
    }

}