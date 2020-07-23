package com.alancamargo.weapons.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import coil.ImageLoader
import coil.api.load
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.tools.hide
import com.alancamargo.weapons.ui.tools.show
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.item_photo.view.*

class ViewPagerAdapter(
    private val data: List<String>,
    private val imageLoader: ImageLoader
) : PagerAdapter() {

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

                imgPhoto.load(data[position], imageLoader) {
                    target(onStart = {
                        txtError.hide()
                        progressBar.show()
                    }, onError = {
                        progressBar.hide()
                        txtError.show()
                    }, onSuccess = {
                        progressBar.hide()
                        imgPhoto.setImageDrawable(it)
                    })
                }

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