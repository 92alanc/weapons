package com.alancamargo.weapons.home.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.home.databinding.ItemQueryBinding
import com.alancamargo.weapons.home.ui.model.WeaponQueryType

internal class QueryViewHolder(
    private val binding: ItemQueryBinding,
    private val onItemClick: (WeaponQueryType) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(query: WeaponQueryType) = with(binding) {
        imgQuery.setImageResource(query.iconId)
        txtQuery.setText(query.labelId)
        root.setOnClickListener {
            onItemClick(query)
        }
    }
}
