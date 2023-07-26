package com.alancamargo.weapons.catalogue.ui.viewmodel.weapondetails

import com.alancamargo.weapons.catalogue.ui.model.UiLabelledWeapon

internal data class WeaponDetailsViewState(val weapon: UiLabelledWeapon? = null) {

    fun setWeapon(weapon: UiLabelledWeapon) = copy(weapon = weapon)
}
