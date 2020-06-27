package com.alancamargo.weapons.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.ui.queries.WeaponQueryType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_query.*

class QueryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

    override val containerView: View? = itemView

    fun bindTo(query: WeaponQueryType) {
        imgQuery.setImageResource(query.iconId)
        txtQuery.setText(query.labelId)
    }

}