package com.alancamargo.weapons.core.consent

import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.alancamargo.weapons.core.log.Logger
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
class UserConsentManagerImpl @Inject constructor(
    private val logger: Logger
) : UserConsentManager {

    private val hasInitialisedMobileAds = AtomicBoolean(false)

    override fun getConsentIfRequired(activity: AppCompatActivity, onDismiss: () -> Unit) {
        val consentInformation = UserMessagingPlatform.getConsentInformation(activity)

        val debugSettings = ConsentDebugSettings.Builder(activity)
            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
            .addTestDeviceHashedId("129D666BF112594B64904705F0AFEFB5")
            .build()

        val params = ConsentRequestParameters.Builder()
            .setConsentDebugSettings(debugSettings)
            .build()

        consentInformation.requestConsentInfoUpdate(
            activity,
            params,
            {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
                    formError?.let { logger.debug(it.message) }

                    if (consentInformation.canRequestAds()) {
                        initialiseMobileAds(activity)
                        onDismiss()
                    }
                }
            },
            { formError ->
                logger.debug(formError.message)
            }
        )

        if (consentInformation.canRequestAds()) {
            initialiseMobileAds(activity)
            onDismiss()
        }
    }

    override fun showPrivacyOptions(activity: AppCompatActivity) {
        UserMessagingPlatform.showPrivacyOptionsForm(activity) { formError ->
            formError?.let { logger.debug(it.message) }
        }
    }

    private fun initialiseMobileAds(activity: AppCompatActivity) {
        if (!hasInitialisedMobileAds.getAndSet(true)) {
            MobileAds.initialize(activity)
        }
    }
}
