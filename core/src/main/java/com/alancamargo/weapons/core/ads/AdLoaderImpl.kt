package com.alancamargo.weapons.core.ads

import android.view.View
import androidx.annotation.VisibleForTesting
import com.alancamargo.weapons.core.log.Logger
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import javax.inject.Inject

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
class AdLoaderImpl @Inject constructor(
    private val logger: Logger
) : AdLoader {

    override fun loadBannerAds(target: View) {
        (target as? AdView)?.run {
            val adRequest = AdRequest.Builder().build()
            adListener = object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)
                    logger.debug("Error loading ads. Reason: ${error.message} code: ${error.code}")
                }
            }
            loadAd(adRequest)
        }
    }
}
