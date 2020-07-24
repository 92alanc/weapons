package com.alancamargo.weapons.ui.viewholder

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
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

        imgPhoto.load(weapon.photos.first(), imageLoader) {
            target(onStart = {
                txtError.isVisible = false
                progressBar.isVisible = true
            }, onError = {
                progressBar.isVisible = false
                txtError.isVisible = true
            }, onSuccess = {
                progressBar.isVisible = false
                imgPhoto.setImageDrawable(it)
            })
        }
    }

}