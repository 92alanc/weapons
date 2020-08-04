package com.alancamargo.weapons.ui.entities.conversions

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.weapons.R
import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.ui.entities.*
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class UiTypeConversionsKtTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun weapon_fromDomainToUi() {
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

        val actual = domain.fromDomainToUi(context)

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

    // region WeaponType
    @Test
    fun melee_fromDomainToUi() {
        val domain = WeaponType.Melee(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_melee))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun pistol_fromDomainToUi() {
        val domain = WeaponType.Pistol(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_pistol))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun boltActionRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.BOLT_ACTION)
        val expected = UiWeaponType(ID, context.getString(R.string.type_rifle_bolt_action))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun semiAutomaticRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        val expected = UiWeaponType(ID, context.getString(R.string.type_rifle_semi_automatic))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun automaticRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.AUTOMATIC)
        val expected = UiWeaponType(ID, context.getString(R.string.type_rifle_automatic))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun shotgun_fromDomainToUi() {
        val domain = WeaponType.Shotgun(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_shotgun))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun carbine_fromDomainToUi() {
        val domain = WeaponType.Carbine(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_carbine))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun lightMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.LIGHT)
        val expected = UiWeaponType(ID, context.getString(R.string.type_machine_gun_light))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun mediumMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.MEDIUM)
        val expected = UiWeaponType(ID, context.getString(R.string.type_machine_gun_medium))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun heavyMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.HEAVY)
        val expected = UiWeaponType(ID, context.getString(R.string.type_machine_gun_heavy))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun subMachineGun_fromDomainToUi() {
        val domain = WeaponType.SubMachineGun(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_sub_machine_gun))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiPersonnelGrenade_fromDomainToUi() {
        val domain = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_PERSONNEL)
        val expected = UiWeaponType(ID, context.getString(R.string.type_grenade_anti_personnel))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiTankGrenade_fromDomainToUi() {
        val domain = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_TANK)
        val expected = UiWeaponType(ID, context.getString(R.string.type_grenade_anti_tank))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiPersonnelMine_fromDomainToUi() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_PERSONNEL)
        val expected = UiWeaponType(ID, context.getString(R.string.type_mine_anti_personnel))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun antiTankMine_fromDomainToUi() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_TANK)
        val expected = UiWeaponType(ID, context.getString(R.string.type_mine_anti_tank))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun grenadeLauncher_fromDomainToUi() {
        val domain = WeaponType.GrenadeLauncher(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_grenade_launcher))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun rocketLauncher_fromDomainToUi() {
        val domain = WeaponType.RocketLauncher(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_rocket_launcher))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun boobyTrap_fromDomainToUi() {
        val domain = WeaponType.BoobyTrap(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_booby_trap))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }

    @Test
    fun flamethrower_fromDomainToUi() {
        val domain = WeaponType.Flamethrower(ID)
        val expected = UiWeaponType(ID, context.getString(R.string.type_flamethrower))

        val actual = domain.fromDomainToUi(context)

        assertThat(actual.id).isEqualTo(expected.id)
    }
    // endregion

    @After
    fun tearDown() {
        stopKoin()
    }

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
    }

}