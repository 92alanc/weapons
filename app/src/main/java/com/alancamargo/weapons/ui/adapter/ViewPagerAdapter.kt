package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.api.load
import com.alancamargo.weapons.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.item_photo.view.*

class ViewPagerAdapter(private val data: List<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return LayoutInflater.from(container.context)
            .inflate(R.layout.item_photo, container, false).apply {
                val indicator = findViewById<MaterialTextView>(R.id.txtIndicator)
                val indicatorText = context.getString(
                    R.string.photo_indicator_format,
                    position + 1,
                    data.size
                )
                indicator.text = indicatorText

                imgPhoto.load(data[position])

                container.addView(this)
            }
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getPageTitle(position: Int): CharSequence? = "${position + 1} / ${data.size}"

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = data.size

}