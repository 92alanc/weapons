package com.alancamargo.weapons.ui.mappers

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.ui.entities.UiCalibre
import com.alancamargo.weapons.util.CALIBRE
import com.alancamargo.weapons.util.CALIBRE_ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UiCalibreMapperTest {

    @Test
    fun shouldConvertCalibreToUiCalibre() {
        val calibre = Calibre(CALIBRE_ID, CALIBRE)
        val mapper = UiCalibreMapper()
        val expected = UiCalibre(CALIBRE_ID, CALIBRE)

        assertThat(mapper.map(calibre)).isEqualTo(expected)
    }

}