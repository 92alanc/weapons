package com.alancamargo.weapons.navigation

import android.content.Context
import androidx.annotation.StringRes

interface WebViewActivityNavigation {

    fun startActivity(
        context: Context,
        @StringRes titleRes: Int,
        url: String
    )
}
