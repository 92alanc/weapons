package com.alancamargo.weapons.catalogue.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.ImageLoader
import com.alancamargo.weapons.R
import com.alancamargo.weapons.databinding.ItemPhotoBinding
import com.alancamargo.weapons.ui.tools.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewPagerAdapter(
    private val data: List<String>,
    private val imageLoader: ImageLoader
) : PagerAdapter() {

    private var _binding: ItemPhotoBinding? = null

    private val binding get() = _binding!!

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        _binding = ItemPhotoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view.apply {
                val indicatorText = context.getString(
                    R.string.photo_indicator_format,
                    position + 1,
                    data.size
                )
                binding.txtIndicator.text = indicatorText
                with(binding) {
                    CoroutineScope(Dispatchers.Main).launch {
                        imgPhoto.load(imageLoader, data[position], progressBar, txtError)
                    }
                }

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