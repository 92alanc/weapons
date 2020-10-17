package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.framework.entities.DbManufacturer
import com.alancamargo.weapons.util.MANUFACTURER_ID
import com.alancamargo.weapons.util.MANUFACTURER_NAME
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DbManufacturerMapperTest {

    private val mapper = DbManufacturerMapper()

    @Test
    fun shouldConvertDbManufacturerToManufacturer() {
        val db = DbManufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)
        val expected = Manufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)

        val actual = mapper.map(db)

        assertThat(actual).isEqualTo(expected)
    }

}