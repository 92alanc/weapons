package com.alancamargo.weapons.home.ui.viewmodel.home

import com.alancamargo.weapons.home.ui.model.WeaponQueryType

internal data class HomeViewState(val queryTypes: List<WeaponQueryType>? = null) {

    fun onQueryTypesReceived(
        queryTypes: List<WeaponQueryType>
    ) = copy(queryTypes = queryTypes)
}
