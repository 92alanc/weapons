package com.alancamargo.weapons.common.ui.model

import androidx.annotation.StringRes
import com.alancamargo.weapons.common.R

enum class UiCountryName(@StringRes val nameRes: Int) {

    BRITISH_EMPIRE(nameRes = R.string.british_empire),
    NAZI_GERMANY(nameRes = R.string.nazi_germany),
    FRANCE(nameRes = R.string.france),
    SOVIET_UNION(nameRes = R.string.soviet_union),
    JAPANESE_EMPIRE(nameRes = R.string.japanese_empire),
    FASCIST_ITALY(nameRes = R.string.fascist_italy),
    UNITED_STATES(nameRes = R.string.united_states),
    UNKNOWN(nameRes = R.string.unknown)
}
