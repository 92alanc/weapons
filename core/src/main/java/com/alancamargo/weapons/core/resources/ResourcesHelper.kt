package com.alancamargo.weapons.core.resources

import android.graphics.drawable.Drawable
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourcesHelper {

    fun getString(@StringRes stringId: Int): String

    fun getDrawable(resourceName: String): Drawable?

    fun getFormattedString(@StringRes stringId: Int, arg: Any): String

    fun getPluralString(@PluralsRes stringId: Int, quantity: Int): String
}
