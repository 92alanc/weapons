package com.alancamargo.weapons.navigation

import android.content.Context
import com.alancamargo.weapons.common.ui.UiWeaponQuery

interface WeaponListActivityNavigation {

    fun startActivity(context: Context, query: UiWeaponQuery)
}
