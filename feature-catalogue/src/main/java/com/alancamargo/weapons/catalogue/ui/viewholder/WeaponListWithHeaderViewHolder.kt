package com.alancamargo.weapons.catalogue.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.databinding.ItemListWithHeaderBinding
import com.alancamargo.weapons.catalogue.databinding.ItemWeaponSquareBinding
import com.alancamargo.weapons.catalogue.ui.adapter.WeaponDiffCallback
import com.alancamargo.weapons.common.ui.UiCalibre
import com.alancamargo.weapons.common.ui.UiCountry
import com.alancamargo.weapons.common.ui.UiManufacturer
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader
import com.alancamargo.weapons.common.ui.UiWeaponType
import com.alancamargo.weapons.common.ui.UiYear
import com.alancamargo.weapons.core.resources.ResourcesHelper

class WeaponListWithHeaderViewHolder(
    private val binding: ItemListWithHeaderBinding,
    private val resourcesHelper: ResourcesHelper,
    onItemClick: (UiWeapon) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val adapter = InnerAdapter(onItemClick)

    fun bindTo(entry: Map.Entry<UiWeaponListHeader?, List<UiWeapon>>) {
        val header = entry.key
        processHeader(header)
        val list = entry.value.sortedBy { it.name }
        binding.recyclerView.adapter = adapter
        adapter.submitList(list)
    }

    private fun processHeader(header: UiWeaponListHeader?) {
        binding.txtHeader.text = when (header) {
            is UiCountry -> {
                val flag = resourcesHelper.getDrawable(header.flagId)

                with(binding.imgFlag) {
                    isVisible = true
                    setImageDrawable(flag)
                }

                header.name
            }

            is UiCalibre -> header.name
            is UiManufacturer -> header.name
            is UiWeaponType -> header.name
            is UiYear -> header.year.toString()
            null -> itemView.context.getString(com.alancamargo.weapons.core.design.R.string.unknown)
            else -> throw IllegalStateException("Must be an implementation of UiWeaponListFilter")
        }
    }

    class InnerAdapter(
        private val onItemClick: (UiWeapon) -> Unit
    ) : ListAdapter<UiWeapon, InnerViewHolder>(WeaponDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemWeaponSquareBinding.inflate(inflater, parent, false)
            return InnerViewHolder(binding, onItemClick)
        }

        override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
            val weapon = getItem(position)
            holder.bindTo(weapon)
        }
    }

    class InnerViewHolder(
        private val binding: ItemWeaponSquareBinding,
        private val onItemClick: (UiWeapon) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(weapon: UiWeapon) = with(binding) {
            txtName.text = weapon.name
            imgPhoto.load(weapon.photos.first())
            root.setOnClickListener {
                onItemClick(weapon)
            }
        }
    }
}
