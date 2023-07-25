package com.alancamargo.weapons.core.resources

import android.graphics.drawable.Drawable
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourcesHelper {

    fun getString(@StringRes stringId: Int): String

    fun getDrawable(resourceName: String): Drawable?

    fun getFormattedStringOrNull(@StringRes stringId: Int, arg: Any?): String?

    fun getPluralStringOrNull(@PluralsRes stringId: Int, quantity: Int?): String?
}
