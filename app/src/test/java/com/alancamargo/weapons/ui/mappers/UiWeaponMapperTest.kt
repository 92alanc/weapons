package com.alancamargo.weapons.ui.mappers

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.weapons.R
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.ui.entities.*
import com.alancamargo.weapons.util.*
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class UiWeaponMapperTest {

    private lateinit var context: Context
    private lateinit var mapper: UiWeaponMapper

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        mapper = UiWeaponMapper(
            UiYearMapper(),
            UiManufacturerMapper(),
            UiCountryMapper(),
            UiWeaponTypeMapper(context),
            UiCalibreMapper()
        )
    }

    @Test
    fun shouldConvertWeaponToUiWeapon() {
        val domain = Weapon(
            ID,
            WEAPON_NAME,
            Year(YEAR_ID, YEAR),
            Manufacturer(MANUFACTURER_ID, MANUFACTURER_NAME),
            Country(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG),
            WeaponType.BoobyTrap(TYPE_ID),
            LENGTH,
            MASS,
            Calibre(CALIBRE_ID, CALIBRE),
            CAPACITY,
            RATE_OF_FIRE,
            EFFECTIVE_RANGE,
            listOf(PHOTO)
        )

        val expected = UiWeapon(
            ID,
            WEAPON_NAME,
            UiYear(YEAR_ID, YEAR),
            UiManufacturer(MANUFACTURER_ID, MANUFACTURER_NAME),
            UiCountry(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG),
            UiWeaponType(TYPE_ID, context.getString(R.string.type_booby_trap)),
            LENGTH,
            MASS,
            UiCalibre(CALIBRE_ID, CALIBRE),
            CAPACITY,
            RATE_OF_FIRE,
            EFFECTIVE_RANGE,
            listOf(PHOTO)
        )

        val actual = mapper.map(domain)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(name).isEqualTo(expected.name)
            assertThat(year).isEqualTo(expected.year)
            assertThat(manufacturer?.id).isEqualTo(expected.manufacturer?.id)
            assertThat(country?.id).isEqualTo(expected.country?.id)
            assertThat(type.id).isEqualTo(expected.type.id)
            assertThat(lengthInMm).isEqualTo(expected.lengthInMm)
            assertThat(massInKg).isEqualTo(expected.massInKg)
            assertThat(calibre?.id).isEqualTo(expected.calibre?.id)
            assertThat(capacityInRounds).isEqualTo(expected.capacityInRounds)
            assertThat(rateOfFireInRpm).isEqualTo(expected.rateOfFireInRpm)
            assertThat(effectiveRangeInM).isEqualTo(expected.effectiveRangeInM)
            assertThat(photos).isEqualTo(expected.photos)
        }
    }

}