package com.alancamargo.weapons.catalogue.data.local

import com.alancamargo.weapons.catalogue.data.db.CalibreDao
import com.alancamargo.weapons.catalogue.data.db.CountryDao
import com.alancamargo.weapons.catalogue.data.db.ManufacturerDao
import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import com.alancamargo.weapons.catalogue.data.db.WeaponTypeDao
import com.alancamargo.weapons.catalogue.data.db.YearDao
import javax.inject.Inject

internal class WeaponLocalDataSourceImpl @Inject constructor(
    private val calibreDao: CalibreDao,
    private val countryDao: CountryDao,
    private val manufacturerDao: ManufacturerDao,
    private val weaponDao: WeaponDao,
    private val weaponTypeDao: WeaponTypeDao,
    private val yearDao: YearDao
) : WeaponLocalDataSource {


}
