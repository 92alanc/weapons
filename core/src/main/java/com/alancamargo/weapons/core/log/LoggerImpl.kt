package com.alancamargo.weapons.core.log

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

private const val LOG_TAG = "WEAPONS_LOG"

internal class LoggerImpl @Inject constructor(
    private val crashlytics: FirebaseCrashlytics
) : Logger {

    override fun debug(message: String) {
        Log.d(LOG_TAG, message)
        crashlytics.log(message)
    }

    override fun error(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message, throwable)
        crashlytics.recordException(throwable)
    }
}
