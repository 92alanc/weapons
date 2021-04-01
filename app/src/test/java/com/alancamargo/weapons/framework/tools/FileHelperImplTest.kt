package com.alancamargo.weapons.framework.tools

import android.content.Context
import android.content.res.AssetManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alancamargo.weapons.TestApp
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.entities.DbCountry
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.FileNotFoundException

private const val COUNTRY_NAME = "German Empire"
private const val INVALID_WEAPON_NAME = "invalid"
private const val VALID_WEAPON_NAME = "MG 08/15"
private const val FORMATTED_WEAPON_NAME = "MG 08-15"

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28], application = TestApp::class)
class FileHelperImplTest {

    @MockK(relaxed = true)
    lateinit var mockAssets: AssetManager

    private lateinit var fileHelper: FileHelperImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        val mockContext = mockk<Context>().apply {
            every { assets } returns mockAssets
        }

        val mockCountryDao = mockk<CountryDao>().apply {
            coEvery {
                getCountryByWeaponName(any())
            } returns DbCountry(1, COUNTRY_NAME, "flag_id")
        }

        fileHelper = FileHelperImpl(mockContext, mockCountryDao)
    }

    @Test
    fun shouldGetImageFilePaths() {
        val expected = arrayOf(
            "$COUNTRY_NAME/$FORMATTED_WEAPON_NAME/1.txt",
            "$COUNTRY_NAME/$FORMATTED_WEAPON_NAME/2.txt",
            "$COUNTRY_NAME/$FORMATTED_WEAPON_NAME/3.txt"
        )

        every {
            mockAssets.list("$COUNTRY_NAME/$FORMATTED_WEAPON_NAME")
        } returns arrayOf("1.txt", "2.txt", "3.txt")

        val paths = runBlocking {
            fileHelper.getImageFilePaths(VALID_WEAPON_NAME)
        }

        assertThat(paths).containsExactlyElementsIn(expected)
    }

    @Test(expected = FileNotFoundException::class)
    fun withInvalidWeaponName_shouldThrowFileNotFoundException() {
        runBlocking {
            fileHelper.getImageFilePaths(INVALID_WEAPON_NAME)
        }
    }

}