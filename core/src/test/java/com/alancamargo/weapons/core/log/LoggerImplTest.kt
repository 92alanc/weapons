package com.alancamargo.weapons.core.log

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.mockk.mockk

class LoggerImplTest {

    private val mockCrashlytics = mockk<FirebaseCrashlytics>(relaxed = true)
    private val logger = LoggerImpl(mockCrashlytics)
}
