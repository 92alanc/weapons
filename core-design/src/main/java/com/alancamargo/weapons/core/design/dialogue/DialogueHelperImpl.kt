package com.alancamargo.weapons.core.design.dialogue

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(
        context: Context,
        title: String,
        messageRes: Int,
        onDismiss: (() -> Unit)?
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(messageRes)
            .setOnDismissListener { onDismiss?.invoke() }
            .show()
    }
}
