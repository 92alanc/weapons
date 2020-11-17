package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Manufacturer
import com.alancamargo.weapons.ui.entities.UiManufacturer
import com.alancamargo.weapons.util.MANUFACTURER_ID
import com.alancamargo.weapons.util.MANUFACTURER_NAME
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UiManufacturerMapperTest {

    @Test
    fun shouldConvertManufacturerToUiManufacturer() {
        val manufacturer = Manufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)
        val mapper = UiManufacturerMapper()
        val expected = UiManufacturer(MANUFACTURER_ID, MANUFACTURER_NAME)

        assertThat(mapper.map(manufacturer)).isEqualTo(expected)
    }

}