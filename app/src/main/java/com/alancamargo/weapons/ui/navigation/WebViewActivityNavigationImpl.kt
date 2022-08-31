package com.alancamargo.weapons.ui.navigation

import android.content.Context
import com.alancamargo.weapons.ui.activities.WebViewActivity

class WebViewActivityNavigationImpl : WebViewActivityNavigation {

    override fun startActivity(context: Context, titleRes: Int, url: String) {
        val args = WebViewActivity.Args(titleRes, url)
        val intent = WebViewActivity.getIntent(context, args)
        context.startActivity(intent)
    }

}
