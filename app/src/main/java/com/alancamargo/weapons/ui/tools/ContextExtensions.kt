package com.alancamargo.weapons.ui.tools

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : AppCompatActivity> Context.createIntent(): Intent {
    return Intent(this, T::class.java)
}
