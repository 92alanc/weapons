package com.alancamargo.weapons.core.consent

import androidx.appcompat.app.AppCompatActivity

interface UserConsentManager {

    fun getConsentIfRequired(activity: AppCompatActivity, onDismiss: () -> Unit)

    fun showPrivacyOptions(activity: AppCompatActivity)
}
