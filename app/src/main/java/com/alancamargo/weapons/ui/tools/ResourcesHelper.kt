package com.alancamargo.weapons.ui.tools

import android.graphics.drawable.Drawable
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourcesHelper {

    fun getDrawable(resourceName: String): Drawable?

    fun getFormattedStringOrNull(@StringRes stringId: Int, arg: Any?): String?

    fun getPluralStringOrNull(@PluralsRes stringId: Int, quantity: Int?): String?

}