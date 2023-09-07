package com.alancamargo.weapons.common.ui.model

import androidx.annotation.StringRes
import com.alancamargo.weapons.common.R

enum class UiCountryName(@StringRes val nameRes: Int) {

    BRITISH_EMPIRE(nameRes = R.string.british_empire),
    GERMAN_EMPIRE(nameRes = R.string.german_empire),
    FRANCE(nameRes = R.string.france),
    AUSTRO_HUNGARIAN_EMPIRE(nameRes = R.string.austro_hungarian_empire),
    RUSSIAN_EMPIRE(nameRes = R.string.russian_empire),
    JAPANESE_EMPIRE(nameRes = R.string.japanese_empire),
    ITALY(nameRes = R.string.italy),
    BELGIUM(nameRes = R.string.belgium),
    SPAIN(nameRes = R.string.spain),
    DENMARK(nameRes = R.string.denmark),
    ROMANIA(nameRes = R.string.romania),
    UNITED_STATES(nameRes = R.string.united_states),
    UNKNOWN(nameRes = R.string.unknown)
}
