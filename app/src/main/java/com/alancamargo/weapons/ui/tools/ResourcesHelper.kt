package com.alancamargo.weapons.ui.tools

import android.graphics.drawable.Drawable

interface ResourcesHelper {

    fun getString(resourceName: String): String

    fun getDrawable(resourceName: String): Drawable?

}