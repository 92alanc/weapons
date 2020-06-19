package com.alancamargo.weapons.framework.model.conversions

import com.alancamargo.weapons.domain.Country
import com.alancamargo.weapons.framework.model.DbCountry
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TypeConversionsKtTest {

    @Test
    fun fromDomainToDb() {
        val domain = Country(name = NAME, flag = FLAG)
        val expected = DbCountry(name = NAME, flag = FLAG)

        val actual = domain.fromDomainToDb()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun fromDbToDomain() {
        val db = DbCountry(name = NAME, flag = FLAG)
        val expected = Country(name = NAME, flag = FLAG)

        val actual = db.fromDbToDomain()

        assertThat(actual).isEqualTo(expected)
    }

    private companion object {
        const val NAME = "United Kingdom"
        const val FLAG = 123
    }

}