package com.alancamargo.weapons.data.io

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IoHelper {

    suspend fun <T> safeIoCall(call: suspend () -> T): Result<T> = withContext(Dispatchers.IO) {
        try {
            Result.Success(call.invoke())
        } catch (t: Throwable) {
            Log.d("TAG", t.message, t) // TODO: replace with Crashlytics
            Result.Error
        }
    }

}