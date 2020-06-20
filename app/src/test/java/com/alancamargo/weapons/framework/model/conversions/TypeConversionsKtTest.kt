package com.alancamargo.weapons.framework.model.conversions

import com.alancamargo.weapons.domain.Calibre
import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.domain.WeaponType
import com.alancamargo.weapons.framework.model.entities.DbCalibre
import com.alancamargo.weapons.framework.model.entities.DbCountry
import com.alancamargo.weapons.framework.model.entities.DbWeaponType
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_BOOBY_TRAP
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_CARBINE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_GRENADE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_GRENADE_LAUNCHER
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_MACHINE_GUN
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_MELEE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_MINE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_PISTOL
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_RIFLE
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_ROCKET_LAUNCHER
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_SHOTGUN
import com.alancamargo.weapons.framework.model.entities.DbWeaponType.Companion.NAME_SUB_MACHINE_GUN
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TypeConversionsKtTest {

    // region Calibre
    @Test
    fun calibre_fromDomainToDb() {
        val domain = Calibre(CALIBRE_ID, CALIBRE)
        val expected = DbCalibre(CALIBRE_ID, CALIBRE)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun calibre_fromDbToDomain() {
        val db = DbCalibre(CALIBRE_ID, CALIBRE)
        val expected = Calibre(CALIBRE_ID, CALIBRE)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region Country
    @Test
    fun country_fromDomainToDb() {
        val domain = Country(ID, COUNTRY_NAME, COUNTRY_FLAG)
        val expected =
            DbCountry(
                ID,
                COUNTRY_NAME,
                COUNTRY_FLAG
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun country_fromDbToDomain() {
        val db = DbCountry(
            ID,
            COUNTRY_NAME,
            COUNTRY_FLAG
        )
        val expected = Country(ID, COUNTRY_NAME, COUNTRY_FLAG)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region WeaponType
    @Test
    fun melee_fromDomainToDb() {
        val domain = WeaponType.Melee(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_MELEE,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun melee_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MELEE,
            category = null
        )
        val expected = WeaponType.Melee(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Melee::class.java)
        }
    }

    @Test
    fun pistol_fromDomainToDb() {
        val domain = WeaponType.Pistol(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_PISTOL,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pistol_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_PISTOL,
            category = null
        )
        val expected = WeaponType.Pistol(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Pistol::class.java)
        }
    }

    @Test
    fun boltActionRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.BOLT_ACTION)
        val expected =
            DbWeaponType(
                ID,
                NAME_RIFLE,
                WeaponType.Rifle.Category.BOLT_ACTION.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boltActionRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            WeaponType.Rifle.Category.BOLT_ACTION.name
        )
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.BOLT_ACTION)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.BOLT_ACTION)
        }
    }

    @Test
    fun semiAutomaticRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        val expected =
            DbWeaponType(
                ID,
                NAME_RIFLE,
                WeaponType.Rifle.Category.SEMI_AUTOMATIC.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun semiAutomaticRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            WeaponType.Rifle.Category.SEMI_AUTOMATIC.name
        )
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SEMI_AUTOMATIC)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        }
    }

    @Test
    fun automaticRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.AUTOMATIC)
        val expected =
            DbWeaponType(
                ID,
                NAME_RIFLE,
                WeaponType.Rifle.Category.AUTOMATIC.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun automaticRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            WeaponType.Rifle.Category.AUTOMATIC.name
        )
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.AUTOMATIC)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.AUTOMATIC)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidRifle_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun shotgun_fromDomainToDb() {
        val domain = WeaponType.Shotgun(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_SHOTGUN,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shotgun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_SHOTGUN,
            category = null
        )
        val expected = WeaponType.Shotgun(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Shotgun::class.java)
        }
    }

    @Test
    fun carbine_fromDomainToDb() {
        val domain = WeaponType.Carbine(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_CARBINE,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun carbine_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_CARBINE,
            category = null
        )
        val expected = WeaponType.Carbine(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Carbine::class.java)
        }
    }

    @Test
    fun lightMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.LIGHT)
        val expected =
            DbWeaponType(
                ID,
                NAME_MACHINE_GUN,
                WeaponType.MachineGun.Category.LIGHT.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun lightMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            WeaponType.MachineGun.Category.LIGHT.name
        )
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.LIGHT)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.LIGHT)
        }
    }

    @Test
    fun generalPurposeMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.GENERAL_PURPOSE)
        val expected =
            DbWeaponType(
                ID,
                NAME_MACHINE_GUN,
                WeaponType.MachineGun.Category.GENERAL_PURPOSE.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun generalPurposeMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            WeaponType.MachineGun.Category.GENERAL_PURPOSE.name
        )
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.GENERAL_PURPOSE)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.GENERAL_PURPOSE)
        }
    }

    @Test
    fun heavyMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.HEAVY)
        val expected =
            DbWeaponType(
                ID,
                NAME_MACHINE_GUN,
                WeaponType.MachineGun.Category.HEAVY.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun heavyMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            WeaponType.MachineGun.Category.HEAVY.name
        )
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.HEAVY)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.HEAVY)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMachineGun_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun subMachineGun_fromDomainToDb() {
        val domain = WeaponType.SubMachineGun(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_SUB_MACHINE_GUN,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun subMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_SUB_MACHINE_GUN,
            category = null
        )
        val expected = WeaponType.SubMachineGun(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.SubMachineGun::class.java)
        }
    }

    @Test
    fun antiPersonnelGrenade_fromDomainToDb() {
        val domain = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_PERSONNEL)
        val expected =
            DbWeaponType(
                ID,
                NAME_GRENADE,
                WeaponType.Grenade.Category.ANTI_PERSONNEL.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelGrenade_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE,
            WeaponType.Grenade.Category.ANTI_PERSONNEL.name
        )
        val expected = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_PERSONNEL)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Grenade::class.java)
            require(this is WeaponType.Grenade)
            assertThat(category).isEqualTo(WeaponType.Grenade.Category.ANTI_PERSONNEL)
        }
    }

    @Test
    fun antiTankGrenade_fromDomainToDb() {
        val domain = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_TANK)
        val expected =
            DbWeaponType(
                ID,
                NAME_GRENADE,
                WeaponType.Grenade.Category.ANTI_TANK.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankGrenade_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE,
            WeaponType.Grenade.Category.ANTI_TANK.name
        )
        val expected = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_TANK)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Grenade::class.java)
            require(this is WeaponType.Grenade)
            assertThat(category).isEqualTo(WeaponType.Grenade.Category.ANTI_TANK)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidGrenade_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun antiPersonnelMine_fromDomainToDb() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_PERSONNEL)
        val expected =
            DbWeaponType(
                ID,
                NAME_MINE,
                WeaponType.Mine.Category.ANTI_PERSONNEL.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelMine_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MINE,
            WeaponType.Mine.Category.ANTI_PERSONNEL.name
        )
        val expected = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_PERSONNEL)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Mine::class.java)
            require(this is WeaponType.Mine)
            assertThat(category).isEqualTo(WeaponType.Mine.Category.ANTI_PERSONNEL)
        }
    }

    @Test
    fun antiTankMine_fromDomainToDb() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_TANK)
        val expected =
            DbWeaponType(
                ID,
                NAME_MINE,
                WeaponType.Mine.Category.ANTI_TANK.name
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankMine_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MINE,
            WeaponType.Mine.Category.ANTI_TANK.name
        )
        val expected = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_TANK)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Mine::class.java)
            require(this is WeaponType.Mine)
            assertThat(category).isEqualTo(WeaponType.Mine.Category.ANTI_TANK)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMine_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            NAME_MINE,
            category = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun grenadeLauncher_fromDomainToDb() {
        val domain = WeaponType.GrenadeLauncher(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_GRENADE_LAUNCHER,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun grenadeLauncher_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE_LAUNCHER,
            category = null
        )
        val expected = WeaponType.GrenadeLauncher(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.GrenadeLauncher::class.java)
        }
    }

    @Test
    fun rocketLauncher_fromDomainToDb() {
        val domain = WeaponType.RocketLauncher(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_ROCKET_LAUNCHER,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun rocketLauncher_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_ROCKET_LAUNCHER,
            category = null
        )
        val expected = WeaponType.RocketLauncher(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.RocketLauncher::class.java)
        }
    }

    @Test
    fun boobyTrap_fromDomainToDb() {
        val domain = WeaponType.BoobyTrap(ID)
        val expected =
            DbWeaponType(
                ID,
                NAME_BOOBY_TRAP,
                category = null
            )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boobyTrap_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_BOOBY_TRAP,
            category = null
        )
        val expected = WeaponType.BoobyTrap(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.BoobyTrap::class.java)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidWeaponType_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            name = "invalid",
            category = null
        )

        db.fromDbToDomain()
    }
    // endregion

    private companion object {
        const val ID = 12345L
        const val COUNTRY_NAME = "United Kingdom"
        const val COUNTRY_FLAG = 1234
        const val CALIBRE_ID = 789L
        const val CALIBRE = ".303 British"
    }

}