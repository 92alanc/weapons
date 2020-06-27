package com.alancamargo.weapons.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.alancamargo.weapons.ui.queries.WeaponQueryType

class QueryViewModel : ViewModel() {

    fun getQueries(): Array<WeaponQueryType> = WeaponQueryType.values()

}