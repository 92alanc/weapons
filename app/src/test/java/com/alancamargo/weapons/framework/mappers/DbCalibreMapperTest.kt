package com.alancamargo.weapons.framework.mappers

import com.alancamargo.weapons.domain.entities.Calibre
import com.alancamargo.weapons.framework.entities.DbCalibre
import com.alancamargo.weapons.util.CALIBRE
import com.alancamargo.weapons.util.CALIBRE_ID
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DbCalibreMapperTest {

    private val mapper = DbCalibreMapper()

    @Test
    fun shouldConvertDbCalibreToCalibre() {
        val db = DbCalibre(CALIBRE_ID, CALIBRE)
        val expected = Calibre(CALIBRE_ID, CALIBRE)

        val actual = mapper.map(db)

        assertThat(actual).isEqualTo(expected)
    }

}