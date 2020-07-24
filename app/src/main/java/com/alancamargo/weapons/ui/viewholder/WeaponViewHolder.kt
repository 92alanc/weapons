package com.alancamargo.weapons.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_weapon.*

class WeaponViewHolder(
    itemView: View,
    private val resourcesHelper: ResourcesHelper,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View? = itemView

    fun bindTo(weapon: UiWeapon) {
        txtName.text = weapon.name
        val flagDrawable = weapon.country?.flagId?.let {
            resourcesHelper.getDrawable(it)
        }
        imgFlag.setImageDrawable(flagDrawable)
        imgPhoto.load(imageLoader, weapon.photos.first(), progressBar, txtError)
    }

}