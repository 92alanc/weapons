package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Year
import com.alancamargo.weapons.framework.entities.DbYear
import com.alancamargo.weapons.util.YEAR
import com.alancamargo.weapons.util.YEAR_ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DbYearMapperTest {

    private val mapper = DbYearMapper()

    @Test
    fun shouldConvertDbYearToYear() {
        val db = DbYear(YEAR_ID, YEAR)
        val expected = Year(YEAR_ID, YEAR)

        val actual = mapper.map(db)

        assertThat(actual).isEqualTo(expected)
    }

}