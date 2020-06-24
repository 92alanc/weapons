package com.alancamargo.weapons.ui.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiCountry(val id: Long, val nameId: String, val flagId: String) : Parcelable