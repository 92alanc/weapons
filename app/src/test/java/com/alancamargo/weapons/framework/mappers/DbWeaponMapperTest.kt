package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.framework.entities.DbWeapon
import com.alancamargo.weapons.util.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DbWeaponMapperTest {

    private val mapper = DbWeaponMapper(
        DbWeaponMapper.Params(
            getYear(),
            getManufacturer(),
            getCountry(),
            getWeaponType(),
            getCalibre(),
            listOf(PHOTO)
        )
    )

    @Test
    fun shouldConvertDbWeaponToWeapon() {
        val db = DbWeapon(
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
            EFFECTIVE_RANGE
        )

        val expected = Weapon(
            ID,
            WEAPON_NAME,
            getYear(),
            getManufacturer(),
            getCountry(),
            getWeaponType(),
            LENGTH,
            MASS,
            getCalibre(),
            CAPACITY,
            RATE_OF_FIRE,
            EFFECTIVE_RANGE,
            listOf(PHOTO)
        )

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(name).isEqualTo(expected.name)
            assertThat(year?.id).isEqualTo(expected.year?.id)
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

    private fun getYear() = Year(YEAR_ID, YEAR)

    private fun getCalibre() = Calibre(CALIBRE_ID, CALIBRE)

    private fun getWeaponType(): WeaponType = WeaponType.BoobyTrap(TYPE_ID)

    private fun getCountry() = Country(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG)

    private fun getManufacturer() = Manufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)

}