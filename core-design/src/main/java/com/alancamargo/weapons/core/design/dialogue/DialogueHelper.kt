package com.alancamargo.weapons.core.design.dialogue

import android.content.Context
import androidx.annotation.StringRes

interface DialogueHelper {

    fun showDialogue(
        context: Context,
        title: String,
        @StringRes messageRes: Int,
        onDismiss: (() -> Unit)? = null
    )

    fun showDialogue(
        context: Context,
        title: String,
        message: String,
        onDismiss: (() -> Unit)? = null
    )
}
