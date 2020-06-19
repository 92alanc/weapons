package com.alancamargo.weapons.framework.model.conversions

import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.domain.WeaponType
import com.alancamargo.weapons.framework.model.DbCountry
import com.alancamargo.weapons.framework.model.DbWeaponType
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_BOOBY_TRAP
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_CARBINE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_GRENADE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_GRENADE_LAUNCHER
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_MACHINE_GUN
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_MELEE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_MINE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_PISTOL
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_RIFLE
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_ROCKET_LAUNCHER
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_SHOTGUN
import com.alancamargo.weapons.framework.model.DbWeaponType.Companion.NAME_SUB_MACHINE_GUN
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TypeConversionsKtTest {

    // region Country
    @Test
    fun country_fromDomainToDb() {
        val domain = Country(name = COUNTRY_NAME, flag = COUNTRY_FLAG)
        val expected = DbCountry(name = COUNTRY_NAME, flag = COUNTRY_FLAG)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun country_fromDbToDomain() {
        val db = DbCountry(name = COUNTRY_NAME, flag = COUNTRY_FLAG)
        val expected = Country(name = COUNTRY_NAME, flag = COUNTRY_FLAG)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region WeaponType
    @Test
    fun melee_fromDomainToDb() {
        val domain = WeaponType.Melee
        val expected = DbWeaponType(name = NAME_MELEE, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun melee_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_MELEE, category = null)
        val expected = WeaponType.Melee

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pistol_fromDomainToDb() {
        val domain = WeaponType.Pistol
        val expected = DbWeaponType(name = NAME_PISTOL, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pistol_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_PISTOL, category = null)
        val expected = WeaponType.Pistol

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boltActionRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(WeaponType.Rifle.Category.BOLT_ACTION)
        val expected = DbWeaponType(
            name = NAME_RIFLE,
            category = WeaponType.Rifle.Category.BOLT_ACTION.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boltActionRifle_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_RIFLE,
            category = WeaponType.Rifle.Category.BOLT_ACTION.name
        )
        val expected = WeaponType.Rifle(WeaponType.Rifle.Category.BOLT_ACTION)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun semiAutomaticRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        val expected = DbWeaponType(
            name = NAME_RIFLE,
            category = WeaponType.Rifle.Category.SEMI_AUTOMATIC.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun semiAutomaticRifle_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_RIFLE,
            category = WeaponType.Rifle.Category.SEMI_AUTOMATIC.name
        )
        val expected = WeaponType.Rifle(WeaponType.Rifle.Category.SEMI_AUTOMATIC)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun automaticRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(WeaponType.Rifle.Category.AUTOMATIC)
        val expected = DbWeaponType(
            name = NAME_RIFLE,
            category = WeaponType.Rifle.Category.AUTOMATIC.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun automaticRifle_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_RIFLE,
            category = WeaponType.Rifle.Category.AUTOMATIC.name
        )
        val expected = WeaponType.Rifle(WeaponType.Rifle.Category.AUTOMATIC)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidRifle_shouldThrowException() {
        val db = DbWeaponType(
            name = NAME_RIFLE,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun shotgun_fromDomainToDb() {
        val domain = WeaponType.Shotgun
        val expected = DbWeaponType(name = NAME_SHOTGUN, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shotgun_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_SHOTGUN, category = null)
        val expected = WeaponType.Shotgun

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun carbine_fromDomainToDb() {
        val domain = WeaponType.Carbine
        val expected = DbWeaponType(name = NAME_CARBINE, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun carbine_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_CARBINE, category = null)
        val expected = WeaponType.Carbine

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun lightMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(WeaponType.MachineGun.Category.LIGHT)
        val expected = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = WeaponType.MachineGun.Category.LIGHT.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun lightMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = WeaponType.MachineGun.Category.LIGHT.name
        )
        val expected = WeaponType.MachineGun(WeaponType.MachineGun.Category.LIGHT)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun generalPurposeMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(WeaponType.MachineGun.Category.GENERAL_PURPOSE)
        val expected = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = WeaponType.MachineGun.Category.GENERAL_PURPOSE.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun generalPurposeMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = WeaponType.MachineGun.Category.GENERAL_PURPOSE.name
        )
        val expected = WeaponType.MachineGun(WeaponType.MachineGun.Category.GENERAL_PURPOSE)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun heavyMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(WeaponType.MachineGun.Category.HEAVY)
        val expected = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = WeaponType.MachineGun.Category.HEAVY.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun heavyMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = WeaponType.MachineGun.Category.HEAVY.name
        )
        val expected = WeaponType.MachineGun(WeaponType.MachineGun.Category.HEAVY)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMachineGun_shouldThrowException() {
        val db = DbWeaponType(
            name = NAME_MACHINE_GUN,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun subMachineGun_fromDomainToDb() {
        val domain = WeaponType.SubMachineGun
        val expected = DbWeaponType(name = NAME_SUB_MACHINE_GUN, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun subMachineGun_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_SUB_MACHINE_GUN, category = null)
        val expected = WeaponType.SubMachineGun

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelGrenade_fromDomainToDb() {
        val domain = WeaponType.Grenade(WeaponType.Grenade.Category.ANTI_PERSONNEL)
        val expected = DbWeaponType(
            name = NAME_GRENADE,
            category = WeaponType.Grenade.Category.ANTI_PERSONNEL.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelGrenade_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_GRENADE,
            category = WeaponType.Grenade.Category.ANTI_PERSONNEL.name
        )
        val expected = WeaponType.Grenade(WeaponType.Grenade.Category.ANTI_PERSONNEL)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankGrenade_fromDomainToDb() {
        val domain = WeaponType.Grenade(WeaponType.Grenade.Category.ANTI_TANK)
        val expected = DbWeaponType(
            name = NAME_GRENADE,
            category = WeaponType.Grenade.Category.ANTI_TANK.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankGrenade_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_GRENADE,
            category = WeaponType.Grenade.Category.ANTI_TANK.name
        )
        val expected = WeaponType.Grenade(WeaponType.Grenade.Category.ANTI_TANK)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidGrenade_shouldThrowException() {
        val db = DbWeaponType(
            name = NAME_GRENADE,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun antiPersonnelMine_fromDomainToDb() {
        val domain = WeaponType.Mine(WeaponType.Mine.Category.ANTI_PERSONNEL)
        val expected = DbWeaponType(
            name = NAME_MINE,
            category = WeaponType.Mine.Category.ANTI_PERSONNEL.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelMine_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_MINE,
            category = WeaponType.Mine.Category.ANTI_PERSONNEL.name
        )
        val expected = WeaponType.Mine(WeaponType.Mine.Category.ANTI_PERSONNEL)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankMine_fromDomainToDb() {
        val domain = WeaponType.Mine(WeaponType.Mine.Category.ANTI_TANK)
        val expected = DbWeaponType(
            name = NAME_MINE,
            category = WeaponType.Mine.Category.ANTI_TANK.name
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankMine_fromDbToDomain() {
        val db = DbWeaponType(
            name = NAME_MINE,
            category = WeaponType.Mine.Category.ANTI_TANK.name
        )
        val expected = WeaponType.Mine(WeaponType.Mine.Category.ANTI_TANK)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMine_shouldThrowException() {
        val db = DbWeaponType(
            name = NAME_MINE,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun grenadeLauncher_fromDomainToDb() {
        val domain = WeaponType.GrenadeLauncher
        val expected = DbWeaponType(name = NAME_GRENADE_LAUNCHER, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun grenadeLauncher_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_GRENADE_LAUNCHER, category = null)
        val expected = WeaponType.GrenadeLauncher

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun rocketLauncher_fromDomainToDb() {
        val domain = WeaponType.RocketLauncher
        val expected = DbWeaponType(name = NAME_ROCKET_LAUNCHER, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun rocketLauncher_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_ROCKET_LAUNCHER, category = null)
        val expected = WeaponType.RocketLauncher

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boobyTrap_fromDomainToDb() {
        val domain = WeaponType.BoobyTrap
        val expected = DbWeaponType(name = NAME_BOOBY_TRAP, category = null)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boobyTrap_fromDbToDomain() {
        val db = DbWeaponType(name = NAME_BOOBY_TRAP, category = null)
        val expected = WeaponType.BoobyTrap

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidWeaponType_shouldThrowException() {
        val db = DbWeaponType(name = "invalid", category = null)

        db.fromDbToDomain()
    }
    // endregion

    private companion object {
        const val COUNTRY_NAME = "United Kingdom"
        const val COUNTRY_FLAG = 123
    }

}