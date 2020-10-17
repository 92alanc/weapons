package com.alancamargo.weapons.ui.activities

import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.repository.weapon.WeaponRepository
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.domain.entities.WeaponListHeader
import com.alancamargo.weapons.domain.entities.WeaponType
import com.alancamargo.weapons.ui.activities.robots.weaponListActivity
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigation
import com.alancamargo.weapons.ui.tools.AdLoader
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class WeaponListActivityTest {

    @Before
    fun setUp() {
        initialiseKoin()
    }

    @Test
    fun shouldShowCorrectNumberOfWeapons() {
        weaponListActivity {
        } assert {
            numberOfWeaponsIs(3)
        }
    }

    @Test
    fun whenClickWeapon_shouldLaunchWeaponDetailsActivity() {
        weaponListActivity {
        } clickWeapon {
            weaponDetailsActivityIsLaunched()
        }
    }

    @Test
    fun shouldLoadAds() {
        weaponListActivity {
        } assert {
            adsAreLoaded()
        }
    }

    private fun initialiseKoin() {
        loadKoinModules(module {
            single<WeaponDetailsActivityNavigation>(override = true) { mockk(relaxed = true) }
            single(override = true) {
                mockk<WeaponRepository>().also {
                    coEvery { it.getWeapons() } returns getWeapons()
                }
            }
            single<AdLoader>(override = true) { mockk(relaxed = true) }
        })
    }

    private fun getWeapons(): Result<Map<WeaponListHeader?, List<Weapon>>> = Result.Success(
        mapOf(null to getWeaponList())
    )

    private fun getWeaponList() = listOf(
        Weapon(
            id = 1,
            name = "Weapon 1",
            year = null,
            manufacturer = null,
            country = null,
            type = WeaponType.BoobyTrap(1),
            lengthInMm = null,
            massInKg = null,
            calibre = null,
            capacityInRounds = null,
            rateOfFireInRpm = null,
            effectiveRangeInM = null,
            photos = emptyList()
        ),
        Weapon(
            id = 2,
            name = "Weapon 2",
            year = null,
            manufacturer = null,
            country = null,
            type = WeaponType.Carbine(2),
            lengthInMm = null,
            massInKg = null,
            calibre = null,
            capacityInRounds = null,
            rateOfFireInRpm = null,
            effectiveRangeInM = null,
            photos = emptyList()
        ),
        Weapon(
            id = 3,
            name = "Weapon 3",
            year = null,
            manufacturer = null,
            country = null,
            type = WeaponType.Flamethrower(3),
            lengthInMm = null,
            massInKg = null,
            calibre = null,
            capacityInRounds = null,
            rateOfFireInRpm = null,
            effectiveRangeInM = null,
            photos = emptyList()
        )
    )

}