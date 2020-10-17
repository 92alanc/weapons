package com.alancamargo.weapons.ui.navigation

import android.content.Context
import com.alancamargo.weapons.ui.entities.UiWeapon

interface WeaponDetailsActivityNavigation {

    fun startActivity(context: Context, weapon: UiWeapon)

}