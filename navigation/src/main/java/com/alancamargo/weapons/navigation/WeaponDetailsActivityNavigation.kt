package com.alancamargo.weapons.navigation

import android.content.Context
import com.alancamargo.weapons.common.ui.UiWeapon

interface WeaponDetailsActivityNavigation {

    fun startActivity(context: Context, weapon: UiWeapon)
}
