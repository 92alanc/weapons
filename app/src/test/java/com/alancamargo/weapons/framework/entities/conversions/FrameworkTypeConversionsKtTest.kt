package com.alancamargo.weapons.framework.entities.conversions

import com.alancamargo.weapons.domain.entities.*
import com.alancamargo.weapons.framework.entities.*
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_ANTI_PERSONNEL
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_ANTI_TANK
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_AUTOMATIC
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_BOLT_ACTION
import com.alancamargo.weapons.framework.entities.DbWeaponType.Companion.CATEGORY_HEAVY
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

    // region Calibre
    @Test
    fun calibre_fromDomainToDb() {
        val domain =
            Calibre(CALIBRE_ID, CALIBRE)
        val expected = DbCalibre(
            CALIBRE_ID,
            CALIBRE
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun calibre_fromDbToDomain() {
        val db = DbCalibre(
            CALIBRE_ID,
            CALIBRE
        )
        val expected =
            Calibre(CALIBRE_ID, CALIBRE)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region Country
    @Test
    fun country_fromDomainToDb() {
        val domain = Country(
            ID,
            COUNTRY_NAME,
            COUNTRY_FLAG
        )
        val expected = DbCountry(
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
        val expected = Country(
            ID,
            COUNTRY_NAME,
            COUNTRY_FLAG
        )

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region Manufacturer
    @Test
    fun manufacturer_fromDomainToDb() {
        val domain = Manufacturer(
            MANUFACTURER_ID,
            MANUFACTURER_NAME
        )
        val expected = DbManufacturer(
            MANUFACTURER_ID,
            MANUFACTURER_NAME
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun manufacturer_fromDbToDomain() {
        val db = DbManufacturer(
            MANUFACTURER_ID,
            MANUFACTURER_NAME
        )
        val expected = Manufacturer(
            MANUFACTURER_ID,
            MANUFACTURER_NAME
        )

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region Year
    @Test
    fun year_fromDbToDomain() {
        val db = DbYear(YEAR_ID, YEAR)
        val expected = Year(YEAR_ID, YEAR)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }
    // endregion

    // region WeaponType
    @Test
    fun melee_fromDomainToDb() {
        val domain = WeaponType.Melee(ID)
        val expected = DbWeaponType(
            ID,
            NAME_MELEE,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun melee_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MELEE,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_PISTOL,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pistol_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_PISTOL,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_BOLT_ACTION
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boltActionRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_BOLT_ACTION
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
        val expected = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_SEMI_AUTOMATIC
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun semiAutomaticRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_SEMI_AUTOMATIC
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
        val expected = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_AUTOMATIC
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun automaticRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_AUTOMATIC
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

    @Test
    fun antiTankRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.ANTI_TANK)
        val expected = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_ANTI_TANK
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_ANTI_TANK
        )
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.ANTI_TANK)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.ANTI_TANK)
        }
    }

    @Test
    fun singleShotRifle_fromDomainToDb() {
        val domain = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SINGLE_SHOT)
        val expected = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_SINGLE_SHOT
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun singleShotRifle_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            CATEGORY_SINGLE_SHOT
        )
        val expected = WeaponType.Rifle(ID, WeaponType.Rifle.Category.SINGLE_SHOT)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Rifle::class.java)
            require(this is WeaponType.Rifle)
            assertThat(category).isEqualTo(WeaponType.Rifle.Category.SINGLE_SHOT)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidRifle_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            NAME_RIFLE,
            categoryId = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun shotgun_fromDomainToDb() {
        val domain = WeaponType.Shotgun(ID)
        val expected = DbWeaponType(
            ID,
            NAME_SHOTGUN,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shotgun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_SHOTGUN,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_CARBINE,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun carbine_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_CARBINE,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            CATEGORY_LIGHT
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun lightMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            CATEGORY_LIGHT
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
    fun mediumMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.MEDIUM)
        val expected = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            CATEGORY_MEDIUM
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun mediumMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            CATEGORY_MEDIUM
        )
        val expected = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.MEDIUM)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.MachineGun::class.java)
            require(this is WeaponType.MachineGun)
            assertThat(category).isEqualTo(WeaponType.MachineGun.Category.MEDIUM)
        }
    }

    @Test
    fun heavyMachineGun_fromDomainToDb() {
        val domain = WeaponType.MachineGun(ID, WeaponType.MachineGun.Category.HEAVY)
        val expected = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            CATEGORY_HEAVY
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun heavyMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MACHINE_GUN,
            CATEGORY_HEAVY
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
            categoryId = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun subMachineGun_fromDomainToDb() {
        val domain = WeaponType.SubMachineGun(ID)
        val expected = DbWeaponType(
            ID,
            NAME_SUB_MACHINE_GUN,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun subMachineGun_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_SUB_MACHINE_GUN,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_GRENADE,
            CATEGORY_ANTI_PERSONNEL
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelGrenade_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE,
            CATEGORY_ANTI_PERSONNEL
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
        val expected = DbWeaponType(
            ID,
            NAME_GRENADE,
            CATEGORY_ANTI_TANK
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankGrenade_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE,
            CATEGORY_ANTI_TANK
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
            categoryId = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun antiPersonnelMine_fromDomainToDb() {
        val domain = WeaponType.Mine(ID, WeaponType.Mine.Category.ANTI_PERSONNEL)
        val expected = DbWeaponType(
            ID,
            NAME_MINE,
            CATEGORY_ANTI_PERSONNEL
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiPersonnelMine_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MINE,
            CATEGORY_ANTI_PERSONNEL
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
        val expected = DbWeaponType(
            ID,
            NAME_MINE,
            CATEGORY_ANTI_TANK
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun antiTankMine_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_MINE,
            CATEGORY_ANTI_TANK
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
            categoryId = "invalid"
        )

        db.fromDbToDomain()
    }

    @Test
    fun grenadeLauncher_fromDomainToDb() {
        val domain = WeaponType.GrenadeLauncher(ID)
        val expected = DbWeaponType(
            ID,
            NAME_GRENADE_LAUNCHER,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun grenadeLauncher_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_GRENADE_LAUNCHER,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_ROCKET_LAUNCHER,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun rocketLauncher_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_ROCKET_LAUNCHER,
            categoryId = null
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
        val expected = DbWeaponType(
            ID,
            NAME_BOOBY_TRAP,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun boobyTrap_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_BOOBY_TRAP,
            categoryId = null
        )
        val expected = WeaponType.BoobyTrap(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.BoobyTrap::class.java)
        }
    }

    @Test
    fun flamethrower_fromDomainToDb() {
        val domain = WeaponType.Flamethrower(ID)
        val expected = DbWeaponType(
            ID,
            NAME_FLAMETHROWER,
            categoryId = null
        )

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun flamethrower_fromDbToDomain() {
        val db = DbWeaponType(
            ID,
            NAME_FLAMETHROWER,
            categoryId = null
        )
        val expected = WeaponType.Flamethrower(ID)

        val actual = db.fromDbToDomain()

        with(actual) {
            assertThat(id).isEqualTo(expected.id)
            assertThat(this).isInstanceOf(WeaponType.Flamethrower::class.java)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidWeaponType_shouldThrowException() {
        val db = DbWeaponType(
            ID,
            nameId = "invalid",
            categoryId = null
        )

        db.fromDbToDomain()
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