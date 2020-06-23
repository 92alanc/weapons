package com.alancamargo.weapons.framework.crash

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashReportHelperImpl : CrashReportHelper {

    override fun log(t: Throwable) {
        FirebaseCrashlytics.getInstance().recordException(t)
    }

}