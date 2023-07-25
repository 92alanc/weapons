package com.alancamargo.weapons.catalogue.ui.mapping

import com.alancamargo.weapons.catalogue.domain.model.Calibre
import com.alancamargo.weapons.catalogue.domain.model.Country
import com.alancamargo.weapons.catalogue.domain.model.Manufacturer
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponType
import com.alancamargo.weapons.catalogue.domain.model.Year
import com.alancamargo.weapons.common.ui.UiWeapon
import com.alancamargo.weapons.common.ui.UiWeaponListHeader

internal fun List<UiWeapon>.createMapFromHeaderType(
    headerClass: Class<WeaponListHeader>
): Map<UiWeaponListHeader?, List<UiWeapon>> {
    return when (headerClass) {
        Calibre::class.java -> groupBy { it.calibre }
        Country::class.java -> groupBy { it.country }
        WeaponType.Melee::class.java,
        WeaponType.Pistol::class.java,
        WeaponType.Rifle::class.java,
        WeaponType.Shotgun::class.java,
        WeaponType.BoobyTrap::class.java,
        WeaponType.Carbine::class.java,
        WeaponType.MachineGun::class.java,
        WeaponType.SubMachineGun::class.java,
        WeaponType.Grenade::class.java,
        WeaponType.Mine::class.java,
        WeaponType.GrenadeLauncher::class.java,
        WeaponType.BoobyTrap::class.java,
        WeaponType.Flamethrower::class.java -> groupBy { it.type }
        Manufacturer::class.java -> groupBy { it.manufacturer }
        Year::class.java -> groupBy { it.year }
        else -> throw IllegalStateException("Must be an implementation of WeaponListHeader")
    }
}
