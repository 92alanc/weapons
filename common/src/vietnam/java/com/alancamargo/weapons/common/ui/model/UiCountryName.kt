package com.alancamargo.weapons.common.ui.model

import androidx.annotation.StringRes
import com.alancamargo.weapons.common.R

enum class UiCountryName(@StringRes val nameRes: Int) {

    SOUTH_VIETNAM(nameRes = R.string.south_vietnam),
    UNITED_STATES(nameRes = R.string.united_states),
    AUSTRALIA(nameRes = R.string.australia),
    NORTH_VIETNAM(nameRes = R.string.north_vietnam),
    VIETCONG(nameRes = R.string.viet_cong),
    UNKNOWN(nameRes = R.string.unknown)
}
