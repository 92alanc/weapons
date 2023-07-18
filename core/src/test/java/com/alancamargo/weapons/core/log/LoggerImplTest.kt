package com.alancamargo.weapons.core.log

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

private const val LOG_TAG = "WEAPONS_LOG"

class LoggerImplTest {

    private val mockCrashlytics = mockk<FirebaseCrashlytics>(relaxed = true)
    private val logger = LoggerImpl(mockCrashlytics)

    @Before
    fun setUp() {
        mockkStatic(Log::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(Log::class)
    }

    @Test
    fun `debug should log message locally`() {
        // GIVEN
        val message = "Message"

        // WHEN
        logger.debug(message)

        // THEN
        verify { Log.d(LOG_TAG, message) }
    }

    @Test
    fun `debug should log message on crashlytics`() {
        // GIVEN
        val message = "Message"

        // WHEN
        logger.debug(message)

        // THEN
        verify { mockCrashlytics.log(message) }
    }

    @Test
    fun `error should log error locally`() {
        // GIVEN
        val throwable = Throwable("Message")

        // WHEN
        logger.error(throwable)

        // THEN
        verify { Log.e(LOG_TAG, throwable.message, throwable) }
    }

    @Test
    fun `error should log error on crashlytics`() {
        // GIVEN
        val throwable = Throwable("Message")

        // WHEN
        logger.error(throwable)

        // THEN
        verify { mockCrashlytics.recordException(throwable) }
    }
}
