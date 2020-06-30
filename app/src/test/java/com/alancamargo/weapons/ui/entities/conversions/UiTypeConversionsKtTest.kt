package com.alancamargo.weapons.ui.entities.conversions

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.ui.entities.UiCalibre
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.ui.entities.UiManufacturer
import com.alancamargo.weapons.ui.entities.UiYear
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UiTypeConversionsKtTest {

    // TODO: use Robolectric
    /*@Test
    fun weapon_fromDomainToUi() {
        val domain = Weapon(
            ID,
            WEAPON_NAME,
            Year(YEAR_ID, YEAR),
            Manufacturer(MANUFACTURER_ID, MANUFACTURER_NAME),
            Country(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG),
            WeaponType.BoobyTrap(TYPE_ID),
            LENGTH,
            WEIGHT,
            Calibre(CALIBRE_ID, CALIBRE),
            CAPACITY,
            RATE_OF_FIRE,
            ACCURACY,
            listOf(PHOTO)
        )

        val expected = UiWeapon(
            ID,
            WEAPON_NAME,
            UiYear(YEAR_ID, YEAR),
            UiManufacturer(MANUFACTURER_ID, MANUFACTURER_NAME),
            UiCountry(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG),
            UiWeaponType.BoobyTrap(TYPE_ID),
            LENGTH,
            WEIGHT,
            UiCalibre(CALIBRE_ID, CALIBRE),
            CAPACITY,
            RATE_OF_FIRE,
            ACCURACY,
            listOf(PHOTO)
        )

        val actual = domain.fromDomainToUi()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(name).isEqualTo(expected.name)
            assertThat(year).isEqualTo(expected.year)
            assertThat(manufacturer.id).isEqualTo(expected.manufacturer.id)
            assertThat(country.id).isEqualTo(expected.country.id)
            assertThat(type.id).isEqualTo(expected.type.id)
            assertThat(length).isEqualTo(expected.length)
            assertThat(weight).isEqualTo(expected.weight)
            assertThat(calibre.id).isEqualTo(expected.calibre.id)
            assertThat(capacity).isEqualTo(expected.capacity)
            assertThat(rateOfFire).isEqualTo(expected.rateOfFire)
            assertThat(accuracy).isEqualTo(expected.accuracy)
            assertThat(photos).isEqualTo(expected.photos)
        }
    }*/

    @Test
    fun country_fromDomainToUi() {
        val domain = Country(
            ID,
            COUNTRY_NAME,
            COUNTRY_FLAG
        )
        val expected = UiCountry(ID, COUNTRY_NAME, COUNTRY_FLAG)

        val actual = domain.fromDomainToUi()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun calibre_fromDomainToUi() {
        val domain =
            Calibre(CALIBRE_ID, CALIBRE)
        val expected = UiCalibre(CALIBRE_ID, CALIBRE)

        val actual = domain.fromDomainToUi()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun manufacturer_fromDomainToUi() {
        val domain = Manufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)
        val expected = UiManufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)

        val actual = domain.fromDomainToUi()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun year_fromDomainToUi() {
        val domain = Year(YEAR_ID, YEAR)
        val expected = UiYear(YEAR_ID, YEAR)

        val actual = domain.fromDomainToUi()

        assertThat(actual).isEqualTo(expected)
    }

    /*// region WeaponType
    @Test
    fun melee_fromDomainToUi() {
        val domain = WeaponType.Melee(ID)
        val expected = UiWeaponType.Melee(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun pistol_fromDomainToUi() {
        val domain = WeaponType.Pistol(ID)
        val expected = UiWeaponType.Pistol(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun boltActionRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.BOLT_ACTION)
        val expected = UiWeaponType.Rifle(ID, UiWeaponType.Rifle.Category.BOLT_ACTION)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun semiAutomaticRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        val expected = UiWeaponType.Rifle(ID, UiWeaponType.Rifle.Category.SEMI_AUTOMATIC)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun automaticRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.AUTOMATIC)
        val expected = UiWeaponType.Rifle(ID, UiWeaponType.Rifle.Category.AUTOMATIC)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun shotgun_fromDomainToUi() {
        val domain = WeaponType.Shotgun(ID)
        val expected = UiWeaponType.Shotgun(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun carbine_fromDomainToUi() {
        val domain = WeaponType.Carbine(ID)
        val expected = UiWeaponType.Carbine(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun lightMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.LIGHT)
        val expected = UiWeaponType.MachineGun(ID, UiWeaponType.MachineGun.Category.LIGHT)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun generalPurposeMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.GENERAL_PURPOSE)
        val expected = UiWeaponType.MachineGun(ID, UiWeaponType.MachineGun.Category.GENERAL_PURPOSE)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun heavyMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.HEAVY)
        val expected = UiWeaponType.MachineGun(ID, UiWeaponType.MachineGun.Category.LIGHT)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun subMachineGun_fromDomainToUi() {
        val domain = WeaponType.SubMachineGun(ID)
        val expected = UiWeaponType.SubMachineGun(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiPersonnelGrenade_fromDomainToUi() {
        val domain = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_PERSONNEL)
        val expected = UiWeaponType.Grenade(ID, UiWeaponType.Grenade.Category.ANTI_PERSONNEL)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiTankGrenade_fromDomainToUi() {
        val domain = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_TANK)
        val expected = UiWeaponType.Grenade(ID, UiWeaponType.Grenade.Category.ANTI_TANK)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiPersonnelMine_fromDomainToUi() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_PERSONNEL)
        val expected = UiWeaponType.Mine(ID, UiWeaponType.Mine.Category.ANTI_PERSONNEL)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiTankMine_fromDomainToUi() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_TANK)
        val expected = UiWeaponType.Mine(ID, UiWeaponType.Mine.Category.ANTI_TANK)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun grenadeLauncher_fromDomainToUi() {
        val domain = WeaponType.GrenadeLauncher(ID)
        val expected = UiWeaponType.GrenadeLauncher(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun rocketLauncher_fromDomainToUi() {
        val domain = WeaponType.RocketLauncher(ID)
        val expected = UiWeaponType.RocketLauncher(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun boobyTrap_fromDomainToUi() {
        val domain = WeaponType.BoobyTrap(ID)
        val expected = UiWeaponType.BoobyTrap(ID)

        val actual = domain.fromDomainToUi()

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun melee_fromUiToStringId() {
        val ui = UiWeaponType.Melee(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_melee)
    }

    @Test
    fun pistol_fromUiToStringId() {
        val ui = UiWeaponType.Pistol(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_pistol)
    }

    @Test
    fun boltActionRifle_fromUiToStringId() {
        val ui = UiWeaponType.Rifle(ID, UiWeaponType.Rifle.Category.BOLT_ACTION)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_rifle_bolt_action)
    }

    @Test
    fun semiAutomaticRifle_fromUiToStringId() {
        val ui = UiWeaponType.Rifle(ID, UiWeaponType.Rifle.Category.SEMI_AUTOMATIC)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_rifle_semi_automatic)
    }

    @Test
    fun automaticRifle_fromUiToStringId() {
        val ui = UiWeaponType.Rifle(ID, UiWeaponType.Rifle.Category.AUTOMATIC)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_rifle_automatic)
    }

    @Test
    fun shotgun_fromUiToStringId() {
        val ui = UiWeaponType.Shotgun(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_shotgun)
    }

    @Test
    fun carbine_fromUiToStringId() {
        val ui = UiWeaponType.Carbine(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_carbine)
    }

    @Test
    fun lightMachineGun_fromUiToStringId() {
        val ui = UiWeaponType.MachineGun(ID, UiWeaponType.MachineGun.Category.LIGHT)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_machine_gun_light)
    }

    @Test
    fun generalPurposeMachineGun_fromUiToStringId() {
        val ui = UiWeaponType.MachineGun(ID, UiWeaponType.MachineGun.Category.GENERAL_PURPOSE)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_machine_gun_general_purpose)
    }

    @Test
    fun heavyMachineGun_fromUiToStringId() {
        val ui = UiWeaponType.MachineGun(ID, UiWeaponType.MachineGun.Category.HEAVY)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_machine_gun_heavy)
    }

    @Test
    fun subMachineGun_fromUiToStringId() {
        val ui = UiWeaponType.SubMachineGun(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_sub_machine_gun)
    }

    @Test
    fun antiPersonnelGrenade_fromUiToStringId() {
        val ui = UiWeaponType.Grenade(ID, UiWeaponType.Grenade.Category.ANTI_PERSONNEL)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_grenade_anti_personnel)
    }

    @Test
    fun antiTankGrenade_fromUiToStringId() {
        val ui = UiWeaponType.Grenade(ID, UiWeaponType.Grenade.Category.ANTI_TANK)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_grenade_anti_tank)
    }

    @Test
    fun antiPersonnelMine_fromUiToStringId() {
        val ui = UiWeaponType.Mine(ID, UiWeaponType.Mine.Category.ANTI_PERSONNEL)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_mine_anti_personnel)
    }

    @Test
    fun antiTankMine_fromUiToStringId() {
        val ui = UiWeaponType.Mine(ID, UiWeaponType.Mine.Category.ANTI_TANK)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_mine_anti_tank)
    }

    @Test
    fun grenadeLauncher_fromUiToStringId() {
        val ui = UiWeaponType.GrenadeLauncher(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_grenade_launcher)
    }

    @Test
    fun rocketLauncher_fromUiToStringId() {
        val ui = UiWeaponType.RocketLauncher(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_rocket_launcher)
    }

    @Test
    fun boobyTrap_fromUiToStringId() {
        val ui = UiWeaponType.BoobyTrap(ID)

        val actual = ui.fromUiToStringId()

        assertThat(actual).isEqualTo(R.string.type_booby_trap)
    }
    // endregion*/

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
        const val WEIGHT = 2.5f
        const val CAPACITY = 10
        const val RATE_OF_FIRE = 20
        const val ACCURACY = 300
        const val PHOTO = "photo1"
    }

}