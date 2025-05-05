package com.alancamargo.weapons.core.design.dialogue

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(
        context: Context,
        title: String,
        messageRes: Int,
        onDismiss: (() -> Unit)?
    ) {
        val message = context.getString(messageRes)
        showDialogue(context, title, message, onDismiss)
    }

    override fun showDialogue(
        context: Context,
        title: String,
        message: String,
        onDismiss: (() -> Unit)?
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setOnDismissListener { onDismiss?.invoke() }
            .show()
    }
}
