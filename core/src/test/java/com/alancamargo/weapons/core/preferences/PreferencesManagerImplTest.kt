package com.alancamargo.weapons.core.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class PreferencesManagerImplTest {

    private val mockSharedPreferences = mockk<SharedPreferences>(relaxed = true)
    private val preferencesManager = PreferencesManagerImpl(mockSharedPreferences)

    @Test
    fun `getBoolean should return boolean value`() {
        // GIVEN
        val key = "boolean"
        val defaultValue = true
        every { mockSharedPreferences.getBoolean(key, defaultValue) } returns false

        // WHEN
        val actual = preferencesManager.getBoolean(key, defaultValue)

        // THEN
        assertThat(actual).isFalse()
    }

    @Test
    fun `setBoolean should set boolean value`() {
        // GIVEN
        val key = "boolean"
        val expected = true

        // WHEN
        preferencesManager.setBoolean(key, expected)

        // THEN
        verify {
            mockSharedPreferences.edit {
                putBoolean(key, expected)
            }
        }
    }
}
