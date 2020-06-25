package com.alancamargo.weapons.ui.tools

import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun AdView.loadAds() {
    val request = AdRequest.Builder().build()
    loadAd(request)
}