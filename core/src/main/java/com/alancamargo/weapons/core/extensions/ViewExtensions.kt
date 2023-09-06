package com.alancamargo.weapons.core.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.setTextOrHide(text: String?) {
    text?.let {
        setText(text)
    } ?: hide()
}

fun MaterialTextView.setTextOrHide(textRes: Int?) {
    textRes?.let {
        setText(it)
    } ?: hide()
}

fun ImageView.setDrawableOrHide(drawable: Drawable?) {
    drawable?.let {
        setImageDrawable(it)
    } ?: hide()
}

private fun View.hide() {
    isVisible = false
}
