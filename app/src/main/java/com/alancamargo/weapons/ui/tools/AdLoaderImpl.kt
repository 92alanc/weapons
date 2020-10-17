package com.alancamargo.weapons.ui.tools

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class AdLoaderImpl : AdLoader {

    override fun loadAds(adView: AdView) {
        val request = AdRequest.Builder().build()
        adView.loadAd(request)
    }

}