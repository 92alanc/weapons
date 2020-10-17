package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.framework.entities.DbCountry
import com.alancamargo.weapons.util.COUNTRY_FLAG
import com.alancamargo.weapons.util.COUNTRY_NAME
import com.alancamargo.weapons.util.ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DbCountryMapperTest {

    private val mapper = DbCountryMapper()

    @Test
    fun shouldConvertDbCountryToCountry() {
        val db = DbCountry(ID, COUNTRY_NAME, COUNTRY_FLAG)
        val expected = Country(ID, COUNTRY_NAME, COUNTRY_FLAG)

        val actual = mapper.map(db)

        assertThat(actual).isEqualTo(expected)
    }

}