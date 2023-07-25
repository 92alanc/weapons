package com.alancamargo.weapons.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.alancamargo.weapons.catalogue.R
import com.alancamargo.weapons.catalogue.databinding.ItemPhotoBinding

internal class ViewPagerAdapter(
    private val data: List<String>
) : PagerAdapter() {

    private var _binding: ItemPhotoBinding? = null

    private val binding
        get() = _binding!!

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        _binding = ItemPhotoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view.apply {
            val indicatorText = context.getString(
                com.alancamargo.weapons.core.design.R.string.photo_indicator_format,
                position + 1,
                data.size
            )
            binding.txtIndicator.text = indicatorText
            binding.imgPhoto.load(data[position])
            container.addView(this)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
        _binding = null
    }

    override fun getPageTitle(position: Int): CharSequence = "${position + 1} / ${data.size}"

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = data.size
}
