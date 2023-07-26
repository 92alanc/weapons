package com.alancamargo.weapons.catalogue.ui.navigation

import android.content.Context
import com.alancamargo.weapons.catalogue.ui.WeaponListActivity
import com.alancamargo.weapons.common.ui.UiWeaponQuery
import com.alancamargo.weapons.navigation.WeaponListActivityNavigation
import javax.inject.Inject

internal class WeaponListActivityNavigationImpl @Inject constructor(
) : WeaponListActivityNavigation {

    override fun startActivity(context: Context, query: UiWeaponQuery) {
        val args = WeaponListActivity.Args(query)
        val intent = WeaponListActivity.getIntent(context, args)
        context.startActivity(intent)
    }
}
