package com.alancamargo.weapons.framework.tools

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.entities.DbCountry
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config
import java.io.FileNotFoundException

private const val COUNTRY_NAME = "German Empire"
private const val INVALID_WEAPON_NAME = "invalid"
private const val VALID_WEAPON_NAME = "MG 08/15"
private const val FORMATTED_WEAPON_NAME = "MG 08-15"

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class FileHelperImplTest {

    private lateinit var fileHelper: FileHelperImpl

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val mockCountryDao = mockk<CountryDao>().apply {
            coEvery {
                getCountryByWeaponName(any())
            } returns DbCountry(1, COUNTRY_NAME, "flag_id")
        }

        fileHelper = FileHelperImpl(context, mockCountryDao)
    }

    @Test
    fun shouldGetImageFilePaths() {
        val paths = runBlocking {
            fileHelper.getImageFilePaths(VALID_WEAPON_NAME)
        }

        assertThat(paths).containsExactly(
            "$COUNTRY_NAME/$FORMATTED_WEAPON_NAME/1.txt",
            "$COUNTRY_NAME/$FORMATTED_WEAPON_NAME/2.txt",
            "$COUNTRY_NAME/$FORMATTED_WEAPON_NAME/3.txt"
        )
    }

    @Test(expected = FileNotFoundException::class)
    fun withInvalidWeaponName_shouldThrowFileNotFoundException() {
        runBlocking {
            fileHelper.getImageFilePaths(INVALID_WEAPON_NAME)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

}