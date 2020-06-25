package com.alancamargo.weapons.ui.queries

import androidx.annotation.StringRes
import com.alancamargo.weapons.R

sealed class WeaponQuery(@StringRes val labelId: Int) {
    
    object All : WeaponQuery(R.string.query_all_weapons)
    // TODO

}