package com.alancamargo.weapons.catalogue.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alancamargo.weapons.catalogue.databinding.ItemPhotoBinding

internal class PhotoViewHolder(
    private val binding: ItemPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(photoUrl: String) {
        binding.imgPhoto.load(photoUrl)
    }
}
