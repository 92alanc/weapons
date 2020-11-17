package com.alancamargo.weapons.ui.mappers

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.alancamargo.weapons.R
import com.alancamargo.weapons.domain.entities.WeaponType
import com.alancamargo.weapons.ui.entities.UiWeaponType
import com.alancamargo.weapons.util.TYPE_ID
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class UiWeaponTypeMapperTest {

    private lateinit var context: Context
    private lateinit var mapper: UiWeaponTypeMapper

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        mapper = UiWeaponTypeMapper(context)
    }

    @Test
    fun melee_fromDomainToUi() {
        val domain = WeaponType.Melee(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_melee))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Melee")
    }

    @Test
    fun pistol_fromDomainToUi() {
        val domain = WeaponType.Pistol(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_pistol))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Pistol")
    }

    @Test
    fun boltActionRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(TYPE_ID, WeaponType.Rifle.Category.BOLT_ACTION)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_rifle_bolt_action))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Bolt action rifle")
    }

    @Test
    fun semiAutomaticRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(TYPE_ID, WeaponType.Rifle.Category.SEMI_AUTOMATIC)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_rifle_semi_automatic))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Semi-automatic rifle")
    }

    @Test
    fun automaticRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(TYPE_ID, WeaponType.Rifle.Category.AUTOMATIC)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_rifle_automatic))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Automatic rifle")
    }

    @Test
    fun antiTankRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(TYPE_ID, WeaponType.Rifle.Category.ANTI_TANK)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_rifle_anti_tank))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Anti-tank rifle")
    }

    @Test
    fun singleShotRifle_fromDomainToUi() {
        val domain = WeaponType.Rifle(TYPE_ID, WeaponType.Rifle.Category.SINGLE_SHOT)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_rifle_single_shot))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Single shot rifle")
    }

    @Test
    fun shotgun_fromDomainToUi() {
        val domain = WeaponType.Shotgun(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_shotgun))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Shotgun")
    }

    @Test
    fun carbine_fromDomainToUi() {
        val domain = WeaponType.Carbine(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_carbine))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Carbine")
    }

    @Test
    fun lightMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(TYPE_ID, WeaponType.MachineGun.Category.LIGHT)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_machine_gun_light))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Light machine-gun")
    }

    @Test
    fun mediumMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(TYPE_ID, WeaponType.MachineGun.Category.MEDIUM)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_machine_gun_medium))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Medium machine-gun")
    }

    @Test
    fun heavyMachineGun_fromDomainToUi() {
        val domain = WeaponType.MachineGun(TYPE_ID, WeaponType.MachineGun.Category.HEAVY)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_machine_gun_heavy))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Heavy machine-gun")
    }

    @Test
    fun subMachineGun_fromDomainToUi() {
        val domain = WeaponType.SubMachineGun(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_sub_machine_gun))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Sub machine-gun")
    }

    @Test
    fun antiPersonnelGrenade_fromDomainToUi() {
        val domain = WeaponType.Grenade(TYPE_ID, WeaponType.Grenade.Category.ANTI_PERSONNEL)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_grenade_anti_personnel))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Anti-personnel grenade")
    }

    @Test
    fun antiTankGrenade_fromDomainToUi() {
        val domain = WeaponType.Grenade(TYPE_ID, WeaponType.Grenade.Category.ANTI_TANK)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_grenade_anti_tank))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Anti-tank grenade")
    }

    @Test
    fun antiPersonnelMine_fromDomainToUi() {
        val domain = WeaponType.Mine(TYPE_ID, WeaponType.Mine.Category.ANTI_PERSONNEL)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_mine_anti_personnel))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Anti-personnel mine")
    }

    @Test
    fun antiTankMine_fromDomainToUi() {
        val domain = WeaponType.Mine(TYPE_ID, WeaponType.Mine.Category.ANTI_TANK)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_mine_anti_tank))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Anti-tank mine")
    }

    @Test
    fun grenadeLauncher_fromDomainToUi() {
        val domain = WeaponType.GrenadeLauncher(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_grenade_launcher))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Grenade launcher")
    }

    @Test
    fun rocketLauncher_fromDomainToUi() {
        val domain = WeaponType.RocketLauncher(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_rocket_launcher))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Rocket launcher")
    }

    @Test
    fun boobyTrap_fromDomainToUi() {
        val domain = WeaponType.BoobyTrap(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_booby_trap))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Booby trap")
    }

    @Test
    fun flamethrower_fromDomainToUi() {
        val domain = WeaponType.Flamethrower(TYPE_ID)
        val expected = UiWeaponType(TYPE_ID, context.getString(R.string.type_flamethrower))

        val actual = mapper.map(domain)

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.name).isEqualTo("Flamethrower")
    }

}