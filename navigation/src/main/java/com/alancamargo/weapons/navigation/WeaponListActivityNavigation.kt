package com.alancamargo.weapons.navigation

import android.content.Context
import com.alancamargo.weapons.common.ui.WeaponQuery

interface WeaponListActivityNavigation {

    fun startActivity(context: Context, query: WeaponQuery)
}
