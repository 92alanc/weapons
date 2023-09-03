package com.alancamargo.weapons.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.weapons.catalogue.databinding.ItemPhotoBinding

internal class PhotoAdapter(
    private val photoUrls: List<String>
) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoUrl = photoUrls[position]
        holder.bindTo(photoUrl)
    }

    override fun getItemCount(): Int = photoUrls.size
}
