package com.alancamargo.weapons.framework.entities.conversions

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.framework.entities.DbWeapon
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FrameworkTypeConversionsKtTest {

    // region Weapon
    @Test
    fun weapon_fromDbToDomain() {
        val manufacturer = Manufacturer(
            MANUFACTURER_ID,
            MANUFACTURER_NAME
        )
        val country = Country(
            COUNTRY_ID,
            COUNTRY_NAME,
            COUNTRY_FLAG
        )
        val type = WeaponType.BoobyTrap(TYPE_ID)
        val calibre = Calibre(CALIBRE_ID, CALIBRE)
        val year = Year(YEAR_ID, YEAR)

        val dbWeapon = DbWeapon(
            ID,
            WEAPON_NAME,
            YEAR_ID,
            MANUFACTURER_ID,
            COUNTRY_ID,
            TYPE_ID,
            LENGTH,
            MASS,
            CALIBRE_ID,
            CAPACITY,
            RATE_OF_FIRE,
            EFFECTIVE_RANGE,
            PHOTOS_JSON
        )

        val expected = Weapon(
            ID,
            WEAPON_NAME,
            year,
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

        val actual = dbWeapon.fromDbToDomain(manufacturer, country, type, calibre, year)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(name).isEqualTo(expected.name)
            assertThat(year.id).isEqualTo(expected.year?.id)
            assertThat(manufacturer.id).isEqualTo(expected.manufacturer?.id)
            assertThat(country.id).isEqualTo(expected.country?.id)
            assertThat(type.id).isEqualTo(expected.type.id)
            assertThat(lengthInMm).isEqualTo(expected.lengthInMm)
            assertThat(massInKg).isEqualTo(expected.massInKg)
            assertThat(calibre.id).isEqualTo(expected.calibre?.id)
            assertThat(capacityInRounds).isEqualTo(expected.capacityInRounds)
            assertThat(rateOfFireInRpm).isEqualTo(expected.rateOfFireInRpm)
            assertThat(effectiveRangeInM).isEqualTo(expected.effectiveRangeInM)
            assertThat(photos).isEqualTo(expected.photos)
        }
    }
    // endregion

    private companion object {
        const val ID = 12345L
        const val COUNTRY_NAME = "United Kingdom"
        const val COUNTRY_FLAG = "flag_uk"
        const val CALIBRE_ID = 789L
        const val CALIBRE = ".303 British"
        const val WEAPON_NAME = "Short Magazine Lee-Enfield No.1 Mk.3"
        const val YEAR_ID = 999L
        const val YEAR = 1907
        const val MANUFACTURER_ID = 999L
        const val MANUFACTURER_NAME = "Lee-Enfield"
        const val COUNTRY_ID = 222L
        const val TYPE_ID = 333L
        const val LENGTH = 102
        const val MASS = 2.5f
        const val CAPACITY = 10
        const val RATE_OF_FIRE = 20
        const val EFFECTIVE_RANGE = 300
        const val PHOTO = "photo1"
        const val PHOTOS_JSON = "[\"$PHOTO\"]"
    }

}