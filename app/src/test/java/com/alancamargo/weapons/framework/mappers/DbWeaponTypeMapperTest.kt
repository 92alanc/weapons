package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.WeaponType
import com.alancamargo.weapons.framework.entities.DbWeaponType
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_ANTI_PERSONNEL
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_ANTI_TANK
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_AUTOMATIC
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_BOLT_ACTION
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_HEAVY
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_LEVER_ACTION
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_LIGHT
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_MEDIUM
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_SEMI_AUTOMATIC
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_SINGLE_SHOT
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_BOOBY_TRAP
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_CARBINE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_FLAMETHROWER
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_GRENADE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_GRENADE_LAUNCHER
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_MACHINE_GUN
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_MELEE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_MINE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_PISTOL
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_RIFLE
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_ROCKET_LAUNCHER
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_SHOTGUN
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.NAME_SUB_MACHINE_GUN
import com.alancamargo.weapons.util.ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DbWeaponTypeMapperTest {

    private val mapper = DbWeaponTypeMapper()

    @Test
    fun melee_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_MELEE, categoryId = null)
        val expected = WeaponType.Melee(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Melee::class.java)
        }
    }

    @Test
    fun pistol_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_PISTOL, categoryId = null)
        val expected = WeaponType.Pistol(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Pistol::class.java)
        }
    }

    @Test
    fun boltActionRifle_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_RIFLE, CATEGORY_BOLT_ACTION)
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.BOLT_ACTION)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.BOLT_ACTION)
        }
    }

    @Test
    fun semiAutomaticRifle_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_RIFLE, CATEGORY_SEMI_AUTOMATIC)
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SEMI_AUTOMATIC)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        }
    }

    @Test
    fun automaticRifle_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_RIFLE, CATEGORY_AUTOMATIC)
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.AUTOMATIC)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.AUTOMATIC)
        }
    }

    @Test
    fun antiTankRifle_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_RIFLE, CATEGORY_ANTI_TANK)
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.ANTI_TANK)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.ANTI_TANK)
        }
    }

    @Test
    fun singleShotRifle_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_RIFLE, CATEGORY_SINGLE_SHOT)
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SINGLE_SHOT)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.SINGLE_SHOT)
        }
    }

    @Test
    fun leverActionRifle_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_RIFLE, CATEGORY_LEVER_ACTION)
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.LEVER_ACTION)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.LEVER_ACTION)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidRifle_shouldThrowException() {
        val db = DbWeaponType(ID, NAME_RIFLE, categoryId = "invalid")
        mapper.map(db)
    }

    @Test
    fun shotgun_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_SHOTGUN, categoryId = null)
        val expected = WeaponType.Shotgun(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Shotgun::class.java)
        }
    }

    @Test
    fun carbine_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_CARBINE, categoryId = null)
        val expected = WeaponType.Carbine(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Carbine::class.java)
        }
    }

    @Test
    fun lightMachineGun_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_MACHINE_GUN, CATEGORY_LIGHT)
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.LIGHT)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.LIGHT)
        }
    }

    @Test
    fun mediumMachineGun_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_MACHINE_GUN, CATEGORY_MEDIUM)
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.MEDIUM)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.MEDIUM)
        }
    }

    @Test
    fun heavyMachineGun_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_MACHINE_GUN, CATEGORY_HEAVY)
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.HEAVY)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.HEAVY)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMachineGun_shouldThrowException() {
        val db = DbWeaponType(ID, NAME_MACHINE_GUN, categoryId = "invalid")
        mapper.map(db)
    }

    @Test
    fun subMachineGun_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_SUB_MACHINE_GUN, categoryId = null)
        val expected = WeaponType.SubMachineGun(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.SubMachineGun::class.java)
        }
    }

    @Test
    fun antiPersonnelGrenade_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_GRENADE, CATEGORY_ANTI_PERSONNEL)
        val expected = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_PERSONNEL)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Grenade::class.java)
            require(this is WeaponType.Grenade)
            assertThat(category).isEqualTo(WeaponType.Grenade.Category.ANTI_PERSONNEL)
        }
    }

    @Test
    fun antiTankGrenade_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_GRENADE, CATEGORY_ANTI_TANK)
        val expected = WeaponType.Grenade(ID, WeaponType.Grenade.Category.ANTI_TANK)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Grenade::class.java)
            require(this is WeaponType.Grenade)
            assertThat(category).isEqualTo(WeaponType.Grenade.Category.ANTI_TANK)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidGrenade_shouldThrowException() {
        val db = DbWeaponType(ID, NAME_GRENADE, categoryId = "invalid")
        mapper.map(db)
    }

    @Test
    fun antiPersonnelMine_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_MINE, CATEGORY_ANTI_PERSONNEL)
        val expected = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_PERSONNEL)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Mine::class.java)
            require(this is WeaponType.Mine)
            assertThat(category).isEqualTo(WeaponType.Mine.Category.ANTI_PERSONNEL)
        }
    }

    @Test
    fun antiTankMine_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_MINE, CATEGORY_ANTI_TANK)
        val expected = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_TANK)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Mine::class.java)
            require(this is WeaponType.Mine)
            assertThat(category).isEqualTo(WeaponType.Mine.Category.ANTI_TANK)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidMine_shouldThrowException() {
        val db = DbWeaponType(ID, NAME_MINE, categoryId = "invalid")
        mapper.map(db)
    }

    @Test
    fun grenadeLauncher_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_GRENADE_LAUNCHER, categoryId = null)
        val expected = WeaponType.GrenadeLauncher(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.GrenadeLauncher::class.java)
        }
    }

    @Test
    fun rocketLauncher_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_ROCKET_LAUNCHER, categoryId = null)
        val expected = WeaponType.RocketLauncher(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.RocketLauncher::class.java)
        }
    }

    @Test
    fun boobyTrap_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_BOOBY_TRAP, categoryId = null)
        val expected = WeaponType.BoobyTrap(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.BoobyTrap::class.java)
        }
    }

    @Test
    fun flamethrower_fromDbToDomain() {
        val db = DbWeaponType(ID, NAME_FLAMETHROWER, categoryId = null)
        val expected = WeaponType.Flamethrower(ID)

        val actual = mapper.map(db)

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Flamethrower::class.java)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidWeaponType_shouldThrowException() {
        val db = DbWeaponType(ID, nameId = "invalid", categoryId = null)
        mapper.map(db)
    }

}