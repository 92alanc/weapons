package com.alancamargo.weapons.core.design.toast

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

internal class ToastHelperImpl @Inject constructor() : ToastHelper {

    override fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
