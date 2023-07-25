package com.alancamargo.weapons.home.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.weapons.home.R

internal enum class WeaponQueryType(@StringRes val labelId: Int, @DrawableRes val iconId: Int) {

    ALL(com.alancamargo.weapons.core.design.R.string.query_all_weapons, com.alancamargo.weapons.core.design.R.drawable.ic_all_weapons),
    BY_NAME(com.alancamargo.weapons.core.design.R.string.query_by_name, com.alancamargo.weapons.core.design.R.drawable.ic_name),
    BY_COUNTRY(com.alancamargo.weapons.core.design.R.string.query_by_country, com.alancamargo.weapons.core.design.R.drawable.ic_country),
    BY_YEAR(com.alancamargo.weapons.core.design.R.string.query_by_year, com.alancamargo.weapons.core.design.R.drawable.ic_year),
    BY_TYPE(com.alancamargo.weapons.core.design.R.string.query_by_type, com.alancamargo.weapons.core.design.R.drawable.ic_type),
    BY_CALIBRE(com.alancamargo.weapons.core.design.R.string.query_by_calibre, com.alancamargo.weapons.core.design.R.drawable.ic_calibre),
    BY_MANUFACTURER(com.alancamargo.weapons.core.design.R.string.query_by_manufacturer, com.alancamargo.weapons.core.design.R.drawable.ic_manufacturer)
}
