package com.alancamargo.weapons.core.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.alancamargo.weapons.core.log.Logger
import javax.inject.Inject

internal class ResourcesHelperImpl @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : ResourcesHelper {

    override fun getDrawable(resourceName: String): Drawable? = try {
        val resId = context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )

        ContextCompat.getDrawable(context, resId)
    } catch (t: Throwable) {
        logger.error(t)
        null
    }

    override fun getFormattedStringOrNull(stringId: Int, arg: Any?): String? = arg?.let {
        context.getString(stringId, it)
    }

    override fun getPluralStringOrNull(stringId: Int, quantity: Int?): String? = quantity?.let {
        context.resources.getQuantityString(stringId, it, it)
    }
}
