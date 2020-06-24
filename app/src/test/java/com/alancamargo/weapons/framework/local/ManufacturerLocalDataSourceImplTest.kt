package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.ManufacturerDao
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ManufacturerLocalDataSourceImplTest {

    @MockK lateinit var mockManufacturerDao: ManufacturerDao

    private lateinit var localDataSource: ManufacturerLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        localDataSource = ManufacturerLocalDataSourceImpl(mockManufacturerDao)
    }

    @Test
    fun shouldGetManufacturers() = runBlocking {
        coEvery {
            mockManufacturerDao.selectAll()
        } returns listOf(mockk(relaxed = true), mockk(relaxed = true))

        val manufacturers = localDataSource.getManufacturers()

        assertThat(manufacturers.size).isEqualTo(2)
    }

    @Test(expected = IOException::class)
    fun getManufacturers_databaseThrowsException_shouldThrow() {
        coEvery { mockManufacturerDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getManufacturers()
        }
    }

}