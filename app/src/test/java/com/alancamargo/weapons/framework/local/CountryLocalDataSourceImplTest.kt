package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.mappers.DbCountryMapper
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

        val mapper = DbCountryMapper()
        localDataSource = CountryLocalDataSourceImpl(mockCountryDao, mapper)
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

    @Test
    fun shouldGetCountryById() = runBlocking {
        coEvery {
            mockCountryDao.selectById(any())
        } returns mockk(relaxed = true)

        val country = localDataSource.getCountryById(1L)

        assertThat(country).isNotNull()
    }

    @Test(expected = IOException::class)
    fun getCountryById_databaseThrowsException_shouldThrow() {
        coEvery { mockCountryDao.selectById(any()) } throws IOException()

        runBlocking {
            localDataSource.getCountryById(1L)
        }
    }

}