package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.YearDao
import com.alancamargo.weapons.framework.mappers.DbYearMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class YearLocalDataSourceImplTest {

    @MockK
    lateinit var mockYearDao: YearDao

    private lateinit var localDataSource: YearLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        val mapper = DbYearMapper()
        localDataSource = YearLocalDataSourceImpl(mockYearDao, mapper)
    }

    @Test
    fun shouldGetYears() = runBlocking {
        coEvery {
            mockYearDao.selectAll()
        } returns listOf(mockk(relaxed = true), mockk(relaxed = true))

        val years = localDataSource.getYears()

        assertThat(years.size).isEqualTo(2)
    }

    @Test(expected = IOException::class)
    fun getYears_databaseThrowsException_shouldThrow() {
        coEvery { mockYearDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getYears()
        }
    }

    @Test
    fun shouldGetYearById() = runBlocking {
        coEvery {
            mockYearDao.selectById(any())
        } returns mockk(relaxed = true)

        val year = localDataSource.getYearById(1L)

        assertThat(year).isNotNull()
    }

    @Test(expected = IOException::class)
    fun getYearById_databaseThrowsException_shouldThrow() {
        coEvery { mockYearDao.selectById(any()) } throws IOException()

        runBlocking {
            localDataSource.getYearById(1L)
        }
    }

}