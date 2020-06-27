package com.alancamargo.weapons.ui.queries

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.weapons.R

enum class WeaponQueryType(@StringRes val labelId: Int, @DrawableRes val iconId: Int) {

    ALL(R.string.query_all_weapons, R.drawable.ic_all_weapons),
    BY_NAME(R.string.query_by_name, R.drawable.ic_name),
    BY_COUNTRY(R.string.query_by_country, R.drawable.ic_country),
    BY_YEAR(R.string.query_by_year, R.drawable.ic_year),
    BY_TYPE(R.string.query_by_type, R.drawable.ic_type),
    BY_CALIBRE(R.string.query_by_calibre, R.drawable.ic_calibre),
    BY_MANUFACTURER(R.string.query_by_manufacturer, R.drawable.ic_manufacturer)

}