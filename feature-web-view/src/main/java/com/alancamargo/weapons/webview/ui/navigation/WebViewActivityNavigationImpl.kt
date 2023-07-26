package com.alancamargo.weapons.webview.ui.navigation

import android.content.Context
import com.alancamargo.weapons.navigation.WebViewActivityNavigation
import com.alancamargo.weapons.webview.ui.WebViewActivity
import javax.inject.Inject

internal class WebViewActivityNavigationImpl @Inject constructor(
) : WebViewActivityNavigation {

    override fun startActivity(context: Context, titleRes: Int, url: String) {
        val args = WebViewActivity.Args(titleRes, url)
        val intent = WebViewActivity.getIntent(context, args)
        context.startActivity(intent)
    }
}
