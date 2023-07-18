package com.alancamargo.weapons.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.alancamargo.weapons.home.ui.model.WeaponQueryType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class QueryViewModel @Inject constructor() : ViewModel() {

    fun getQueries(): List<WeaponQueryType> = WeaponQueryType.values().toList()

    fun onQueryItemClicked(queryType: WeaponQueryType) {
        // TODO
    }
}