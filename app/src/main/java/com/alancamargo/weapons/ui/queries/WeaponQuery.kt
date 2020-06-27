package com.alancamargo.weapons.ui.queries

import android.os.Parcelable
import androidx.annotation.StringRes
import com.alancamargo.weapons.R
import kotlinx.android.parcel.Parcelize

sealed class WeaponQuery(@StringRes val labelId: Int) : Parcelable {

    @Parcelize
    object All : WeaponQuery(R.string.query_all_weapons)
    // TODO

}