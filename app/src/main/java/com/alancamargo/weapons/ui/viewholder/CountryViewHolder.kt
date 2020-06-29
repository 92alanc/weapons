package com.alancamargo.weapons.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_country.*

class CountryViewHolder(
    itemView: View,
    private val resourcesHelper: ResourcesHelper
) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View? = itemView

    fun bindTo(country: UiCountry) {
        val flag = resourcesHelper.getDrawable(country.flagId)
        imgFlag.setImageDrawable(flag)
        txtName.text = country.name
    }

}