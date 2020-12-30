package com.alancamargo.weapons.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.databinding.ItemListWithHeaderBinding
import com.alancamargo.weapons.databinding.ItemWeaponSquareBinding
import com.alancamargo.weapons.ui.adapter.OnItemClickListener
import com.alancamargo.weapons.ui.entities.*
import com.alancamargo.weapons.ui.tools.ResourcesHelper
import com.alancamargo.weapons.ui.tools.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeaponListWithHeaderViewHolder(
    imageLoader: ImageLoader,
    private val binding: ItemListWithHeaderBinding,
    private val resourcesHelper: ResourcesHelper
) : RecyclerView.ViewHolder(binding.root) {

    private var onItemClickListener: OnItemClickListener? = null

    private val adapter by lazy { InnerAdapter(onItemClickListener, imageLoader) }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun bindTo(entry: Map.Entry<UiWeaponListHeader?, List<UiWeapon>>) {
        val header = entry.key
        processHeader(header)
        val list = entry.value.sortedBy { it.name }
        binding.recyclerView.adapter = adapter
        adapter.setData(list)
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
            null -> itemView.context.getString(R.string.unknown)
            else -> throw IllegalStateException("Must be an implementation of UiWeaponListFilter")
        }
    }

    class InnerAdapter(
        private val onItemClickListener: OnItemClickListener?,
        private val imageLoader: ImageLoader
    ) : RecyclerView.Adapter<InnerViewHolder>() {

        private var data: List<UiWeapon> = emptyList()

        fun setData(data: List<UiWeapon>) {
            this.data = data
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
            val inflater = LayoutInflater.from(parent.context)

            val binding = ItemWeaponSquareBinding.inflate(inflater, parent, false)
            return InnerViewHolder(binding, imageLoader)
        }

        override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
            val weapon = data[position]

            with(holder) {
                bindTo(weapon)
                itemView.setOnClickListener {
                    onItemClickListener?.onItemClick(weapon)
                }
            }
        }

        override fun getItemId(position: Int): Long = data[position].id

        override fun getItemCount(): Int = data.size

        override fun getItemViewType(position: Int): Int = position

    }

    class InnerViewHolder(
        private val binding: ItemWeaponSquareBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(weapon: UiWeapon) = with(binding) {
            txtName.text = weapon.name
            CoroutineScope(Dispatchers.Main).launch {
                imgPhoto.load(imageLoader, weapon.photos.first(), progressBar, txtError)
            }
        }

    }

}