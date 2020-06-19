package com.alancamargo.weapons.framework.model.conversions

import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.framework.model.DbCountry

fun Country.fromDomainToDb() = DbCountry(name = name, flag = flag)

fun DbCountry.fromDbToDomain() = Country(name, flag)