package com.alancamargo.weapons.framework.crash

import android.util.Log
import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashReportHelperImpl : CrashReportHelper {

    override fun log(t: Throwable) {
        Log.e("ERROR_WEAPONS", t.message, t)
        FirebaseCrashlytics.getInstance().recordException(t)
    }

}