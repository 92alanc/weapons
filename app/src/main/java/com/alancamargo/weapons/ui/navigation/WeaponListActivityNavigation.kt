package com.alancamargo.weapons.ui.navigation

import android.content.Context
import com.alancamargo.weapons.ui.queries.WeaponQuery

interface WeaponListActivityNavigation {

    fun startActivity(context: Context, query: WeaponQuery)

}