package com.alancamargo.weapons.ui.tools

import android.view.View
import androidx.annotation.StringRes

interface AdLoader {

    fun loadBannerAds(target: View, @StringRes adIdRes: Int)

}