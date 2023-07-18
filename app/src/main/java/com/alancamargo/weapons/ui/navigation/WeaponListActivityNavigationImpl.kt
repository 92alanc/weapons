package com.alancamargo.weapons.ui.navigation

import android.content.Context
import com.alancamargo.weapons.ui.activities.WeaponListActivity
import com.alancamargo.weapons.common.WeaponQuery

class WeaponListActivityNavigationImpl : WeaponListActivityNavigation {

    override fun startActivity(context: Context, query: com.alancamargo.weapons.common.WeaponQuery) {
        val intent = WeaponListActivity.getIntent(context, query)
        context.startActivity(intent)
    }

}