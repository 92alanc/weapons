package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Country
import com.alancamargo.weapons.ui.entities.UiCountry
import com.alancamargo.weapons.util.COUNTRY_FLAG
import com.alancamargo.weapons.util.COUNTRY_ID
import com.alancamargo.weapons.util.COUNTRY_NAME
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UiCountryMapperTest {

    @Test
    fun shouldConvertCountryToUiCountry() {
        val country = Country(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG)
        val mapper = UiCountryMapper()
        val expected = UiCountry(COUNTRY_ID, COUNTRY_NAME, COUNTRY_FLAG)

        assertThat(mapper.map(country)).isEqualTo(expected)
    }

}