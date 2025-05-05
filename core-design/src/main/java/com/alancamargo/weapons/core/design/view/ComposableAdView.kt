package com.alancamargo.weapons.core.design.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.alancamargo.weapons.core.ads.AdLoader
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

const val AD_VIEW_FRACTION = 0.88f

@Composable
fun ComposableAdView(modifier: Modifier, adUnitId: String, adLoader: AdLoader) {
    AndroidView(
        modifier = modifier,
        factory = ::AdView,
        update = { adView ->
            adView.setAdSize(AdSize.BANNER)
            adView.adUnitId = adUnitId
            adLoader.loadBannerAds(adView)
        }
    )
}
