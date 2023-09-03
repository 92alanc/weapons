package com.alancamargo.weapons.home.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alancamargo.weapons.home.R

internal enum class WeaponQueryType(@StringRes val labelId: Int, @DrawableRes val iconId: Int) {

    BY_NAME(R.string.query_by_name, R.drawable.ic_name),
    BY_COUNTRY(R.string.query_by_country, R.drawable.ic_country),
    BY_YEAR(R.string.query_by_year, R.drawable.ic_year),
    BY_TYPE(R.string.query_by_type, R.drawable.ic_type),
    BY_CALIBRE(R.string.query_by_calibre, R.drawable.ic_calibre),
    BY_MAKE(R.string.query_by_make, R.drawable.ic_make)
}
