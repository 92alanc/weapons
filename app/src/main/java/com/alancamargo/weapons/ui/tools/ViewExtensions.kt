package com.alancamargo.weapons.ui.tools

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.textview.MaterialTextView

fun AdView.loadAds() {
    val request = AdRequest.Builder().build()
    loadAd(request)
}

fun MaterialTextView.setTextOrHide(text: String?) {
    text?.let {
        setText(text)
    } ?: hide()
}

fun ImageView.setDrawableOrHide(drawable: Drawable?) {
    drawable?.let {
        setImageDrawable(it)
    } ?: hide()
}

private fun View.hide() {
    visibility = View.GONE
}
