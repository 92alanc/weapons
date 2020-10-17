package com.alancamargo.weapons.framework.mappers.country

import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.framework.entities.DbCountry
import com.alancamargo.weapons.util.COUNTRY_FLAG
import com.alancamargo.weapons.util.COUNTRY_NAME
import com.alancamargo.weapons.util.ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryToDbCountryMapperTest {

    private val mapper = CountryToDbCountryMapper()

    @Test
    fun shouldConvertCountryToDbCountry() {
        val domain = Country(ID, COUNTRY_NAME, COUNTRY_FLAG)
        val expected = DbCountry(ID, COUNTRY_NAME, COUNTRY_FLAG)

        val actual = mapper.map(domain)

        assertThat(actual).isEqualTo(expected)
    }

}