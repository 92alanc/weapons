package com.alancamargo.weapons.ui.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.load
import com.google.android.material.textview.MaterialTextView

class WeaponViewHolder(
    itemView: View,
    private val resourcesHelper: ResourcesHelper,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemView) {

    private val txtName by lazy { itemView.findViewById<MaterialTextView>(R.id.txtName) }
    private val txtError by lazy { itemView.findViewById<MaterialTextView>(R.id.txtError) }
    private val imgFlag by lazy { itemView.findViewById<ImageView>(R.id.imgFlag) }
    private val imgPhoto by lazy { itemView.findViewById<ImageView>(R.id.imgPhoto) }
    private val progressBar by lazy { itemView.findViewById<ProgressBar>(R.id.progressBar) }

    fun bindTo(weapon: UiWeapon) {
        txtName.text = weapon.name

        val flagDrawable = weapon.country?.flagId?.let {
            resourcesHelper.getDrawable(it)
        }

        imgFlag.setImageDrawable(flagDrawable)
        imgPhoto.load(imageLoader, weapon.photos.first(), progressBar, txtError)
    }

}