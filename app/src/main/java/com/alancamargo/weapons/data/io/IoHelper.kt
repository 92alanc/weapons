package com.alancamargo.weapons.data.io

import com.alancamargo.weapons.data.crash.CrashReportHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IoHelper(private val crashReportHelper: CrashReportHelper) {

    suspend fun <T> safeIoCall(call: suspend () -> T): Result<T> = withContext(Dispatchers.IO) {
        try {
            Result.Success(call.invoke())
        } catch (t: Throwable) {
            crashReportHelper.log(t)
            Result.Error
        }
    }

}