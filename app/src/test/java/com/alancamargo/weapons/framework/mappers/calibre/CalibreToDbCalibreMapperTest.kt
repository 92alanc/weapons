package com.alancamargo.weapons.framework.mappers.calibre

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.framework.entities.DbCalibre
import com.alancamargo.weapons.util.CALIBRE
import com.alancamargo.weapons.util.CALIBRE_ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CalibreToDbCalibreMapperTest {

    private val mapper = CalibreToDbCalibreMapper()

    @Test
    fun shouldConvertCalibreToDbCalibre() {
        val domain = Calibre(CALIBRE_ID, CALIBRE)
        val expected = DbCalibre(CALIBRE_ID, CALIBRE)

        val actual = mapper.map(domain)

        assertThat(actual).isEqualTo(expected)
    }

}