package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.ui.entities.UiYear
import com.alancamargo.weapons.util.YEAR
import com.alancamargo.weapons.util.YEAR_ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UiYearMapperTest {

    @Test
    fun shouldConvertYearToUiYear() {
        val year = Year(YEAR_ID, YEAR)
        val mapper = UiYearMapper()
        val expected = UiYear(YEAR_ID, YEAR)

        assertThat(mapper.map(year)).isEqualTo(expected)
    }

}