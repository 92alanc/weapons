package com.alancamargo.weapons.ui.navigation

import android.content.Context
import com.alancamargo.weapons.ui.activities.WeaponDetailsActivity
import com.alancamargo.weapons.ui.entities.UiWeapon

class WeaponDetailsActivityNavigationImpl : WeaponDetailsActivityNavigation {

    override fun startActivity(context: Context, weapon: UiWeapon) {
        val intent = WeaponDetailsActivity.getIntent(context, weapon)
        context.startActivity(intent)
    }

}