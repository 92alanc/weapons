package com.alancamargo.weapons.ui.tools

import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class AdLoaderImpl : AdLoader {

    override fun loadBannerAds(target: View) {
        (target as? AdView)?.let { adView ->
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }
}
