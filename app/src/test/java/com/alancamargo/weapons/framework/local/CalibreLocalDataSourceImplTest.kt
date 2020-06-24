package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.CalibreDao
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CalibreLocalDataSourceImplTest {

    @MockK lateinit var mockCalibreDao: CalibreDao

    private lateinit var localDataSource: CalibreLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        localDataSource = CalibreLocalDataSourceImpl(mockCalibreDao)
    }

    @Test
    fun shouldGetCalibres() = runBlocking {
        coEvery {
            mockCalibreDao.selectAll()
        } returns listOf(mockk(relaxed = true), mockk(relaxed = true))

        val weaponTypes = localDataSource.getCalibres()

        assertThat(weaponTypes.size).isEqualTo(2)
    }

    @Test(expected = IOException::class)
    fun getCalibres_databaseThrowsException_shouldThrow() {
        coEvery { mockCalibreDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getCalibres()
        }
    }

}