package com.alancamargo.weapons.data.io

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IoHelper(private val crashReportHelper: CrashReportHelper) {

    suspend fun <K, V> safeIoCall(call: suspend () -> Map<K, V>): Result<Map<K, V>> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(call.invoke())
            } catch (t: Throwable) {
                crashReportHelper.log(t)
                Result.Error
            }
        }
    }

}