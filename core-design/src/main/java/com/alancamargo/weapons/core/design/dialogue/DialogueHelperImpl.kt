package com.alancamargo.weapons.core.design.dialogue

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

internal class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(context: Context, title: String, messageRes: Int) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(messageRes)
            .show()
    }
}
