package com.alancamargo.weapons.ui.tools

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.android.material.textview.MaterialTextView

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

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun ImageView.load(
    imageLoader: ImageLoader,
    path: String,
    progressBar:
    ProgressBar,
    errorTextView: MaterialTextView
) {
    val inputStream = context.assets.open(path)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    val request = ImageRequest.Builder(context)
        .data(bitmap)
        .target(onStart = {
            errorTextView.isVisible = false
            progressBar.isVisible = true
        }, onError = {
            progressBar.isVisible = false
            errorTextView.isVisible = true
        }, onSuccess = {
            progressBar.isVisible = false
            setImageDrawable(it)
        }).build()

    imageLoader.execute(request)
}

private fun View.hide() {
    visibility = View.GONE
}
