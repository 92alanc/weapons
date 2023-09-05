package com.alancamargo.weapons.core.ads

import android.view.View
import androidx.annotation.VisibleForTesting
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import javax.inject.Inject

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
class AdLoaderImpl @Inject constructor() : AdLoader {

    override fun loadBannerAds(target: View) {
        (target as? AdView)?.let { adView ->
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }
}
