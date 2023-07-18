package com.alancamargo.weapons.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.weapons.home.databinding.ItemQueryBinding
import com.alancamargo.weapons.home.ui.model.WeaponQueryType

internal class QueryAdapter(
    private val onItemClick: (WeaponQueryType) -> Unit
) : ListAdapter<WeaponQueryType, QueryViewHolder>(WeaponQueryTypeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQueryBinding.inflate(inflater, parent, false)
        return QueryViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        val query = getItem(position)
        holder.bindTo(query)
    }
}
