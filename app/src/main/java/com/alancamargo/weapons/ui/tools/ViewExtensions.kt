package com.alancamargo.weapons.ui.tools

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import coil.ImageLoader
import coil.request.LoadRequestBuilder
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

fun ImageView.load(
    imageLoader: ImageLoader,
    url: String,
    progressBar:
    ProgressBar,
    errorTextView: MaterialTextView
) {
    val request = LoadRequestBuilder(context)
        .data(url)
        .target(onStart = {
            errorTextView.isVisible = false
            progressBar.isVisible = true
        }, onError = {
            progressBar.isVisible = false
            errorTextView.isVisible = true
        }, onSuccess = {
            progressBar.isVisible = false
            this.setImageDrawable(it)
        }).build()

    imageLoader.execute(request)
}

private fun View.hide() {
    visibility = View.GONE
}
