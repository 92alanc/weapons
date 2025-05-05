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

    private val mockFirebaseCrashlytics = mockk<FirebaseCrashlytics>(relaxed = true)
    private val logger = LoggerImpl(mockFirebaseCrashlytics)

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
        // Given
        val message = "Message"

        // When
        logger.debug(message)

        // Then
        verify { Log.d(LOG_TAG, message) }
    }

    @Test
    fun `debug should log message on crashlytics`() {
        // Given
        val message = "Message"

        // When
        logger.debug(message)

        // Then
        verify { mockFirebaseCrashlytics.log(message) }
    }

    @Test
    fun `error should log error locally`() {
        // Given
        val throwable = Throwable("Message")

        // When
        logger.error(throwable)

        // Then
        verify { Log.e(LOG_TAG, throwable.message, throwable) }
    }

    @Test
    fun `error should log error on crashlytics`() {
        // Given
        val throwable = Throwable("Message")

        // When
        logger.error(throwable)

        // Then
        verify { mockFirebaseCrashlytics.recordException(throwable) }
    }
}
