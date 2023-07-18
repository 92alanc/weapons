package com.alancamargo.weapons.core.design.ads

import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import javax.inject.Inject

internal class AdLoaderImpl @Inject constructor() : AdLoader {

    override fun loadBannerAds(target: View) {
        (target as? AdView)?.let { adView ->
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }
}
