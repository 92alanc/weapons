package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.CountryDao
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CountryLocalDataSourceImplTest {

    @MockK lateinit var mockCountryDao: CountryDao

    private lateinit var localDataSource: CountryLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        localDataSource = CountryLocalDataSourceImpl(mockCountryDao)
    }

    @Test
    fun shouldGetCountries() = runBlocking {
        coEvery {
            mockCountryDao.selectAll()
        } returns listOf(mockk(relaxed = true), mockk(relaxed = true))

        val countries = localDataSource.getCountries()

        assertThat(countries.size).isEqualTo(2)
    }

    @Test(expected = IOException::class)
    fun getCountries_databaseThrowsException_shouldThrow() {
        coEvery { mockCountryDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getCountries()
        }
    }

}