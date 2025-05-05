package com.alancamargo.weapons.core.resources

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.alancamargo.weapons.core.log.Logger
import javax.inject.Inject

internal class ResourcesHelperImpl @Inject constructor(
    private val context: Context,
    private val logger: Logger
) : ResourcesHelper {

    override fun getString(stringId: Int): String {
        return context.getString(stringId)
    }

    override fun getDrawable(resourceName: String): Drawable? = try {
        getDrawableResourceId(resourceName)?.let { resId ->
            ContextCompat.getDrawable(context, resId)
        }
    } catch (t: Throwable) {
        logger.error(t)
        null
    }

    @SuppressLint("DiscouragedApi")
    override fun getDrawableResourceId(resourceName: String): Int? {
        return try {
            context.resources.getIdentifier(
                resourceName,
                "drawable",
                context.packageName
            )
        } catch (t: Throwable) {
            logger.error(t)
            null
        }
    }

    override fun getFormattedString(stringId: Int, arg: Any): String {
        return context.getString(stringId, arg)
    }

    override fun getPluralString(stringId: Int, quantity: Int): String {
        return context.resources.getQuantityString(stringId, quantity, quantity)
    }
}
