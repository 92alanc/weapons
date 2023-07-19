package com.alancamargo.weapons.catalogue.ui.navigation

import android.content.Context
import com.alancamargo.weapons.catalogue.ui.WeaponDetailsActivity
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.navigation.WeaponDetailsActivityNavigation
import javax.inject.Inject

internal class WeaponDetailsActivityNavigationImpl @Inject constructor(
) : WeaponDetailsActivityNavigation {

    override fun startActivity(context: Context, weapon: UiWeapon) {
        val intent = WeaponDetailsActivity.getIntent(context, weapon)
        context.startActivity(intent)
    }
}
