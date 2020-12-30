package com.alancamargo.weapons.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.databinding.ItemQueryBinding
import com.alancamargo.weapons.ui.queries.WeaponQueryType

class QueryViewHolder(
    private val binding: ItemQueryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(query: WeaponQueryType) = with(binding) {
        imgQuery.setImageResource(query.iconId)
        txtQuery.setText(query.labelId)
    }

}