package com.alancamargo.weapons.catalogue.ui.model

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes

internal data class UiLabelledCountry(
    @StringRes val nameRes: Int,
    val flagDrawable: Drawable
)
