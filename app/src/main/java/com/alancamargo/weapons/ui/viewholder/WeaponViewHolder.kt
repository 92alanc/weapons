package com.alancamargo.weapons.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weapon.*

class WeaponViewHolder(
    itemView: View,
    private val resourcesHelper: ResourcesHelper
) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View? = itemView

    fun bindTo(weapon: UiWeapon) {
        txtName.text = weapon.name
        val flagDrawable = resourcesHelper.getDrawable(weapon.country.flagId)
        imgFlag.setImageDrawable(flagDrawable)
        imgPhoto.load(weapon.photos.first())
    }

}