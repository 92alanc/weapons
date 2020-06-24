package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.CalibreLocalDataSource
import com.alancamargo.weapons.data.local.CountryLocalDataSource
import com.alancamargo.weapons.data.local.ManufacturerLocalDataSource
import com.alancamargo.weapons.data.local.WeaponTypeLocalDataSource
import com.alancamargo.weapons.domain.WeaponType
import com.alancamargo.weapons.framework.db.WeaponDao
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class WeaponLocalDataSourceImplTest {

    @MockK lateinit var mockWeaponDao: WeaponDao
    @MockK lateinit var mockWeaponTypeLocalDataSource: WeaponTypeLocalDataSource
    @MockK lateinit var mockCountryLocalDataSource: CountryLocalDataSource
    @MockK lateinit var mockCalibreLocalDataSource: CalibreLocalDataSource
    @MockK lateinit var mockManufacturerLocalDataSource: ManufacturerLocalDataSource

    private lateinit var localDataSource: WeaponLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localDataSource = WeaponLocalDataSourceImpl(
            mockWeaponDao,
            mockWeaponTypeLocalDataSource,
            mockCountryLocalDataSource,
            mockCalibreLocalDataSource,
            mockManufacturerLocalDataSource
        )
    }

    @Test
    fun shouldGetWeapons() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeapons()

        assertThat(weapons.size).isEqualTo(3)
    }

    @Test(expected = IOException::class)
    fun getWeapons_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeapons()
        }
    }

    @Test
    fun shouldGetWeaponsByYear() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByYear(1949)

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByYear_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectByYear(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByYear(1967)
        }
    }

    @Test
    fun shouldGetWeaponsByCountry() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByCountry(11L)

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByCountry_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectByCountry(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByCountry(22L)
        }
    }

    @Test
    fun shouldGetWeaponsByType() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByType(12L)

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByType_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectByType(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByType(33L)
        }
    }

    @Test
    fun shouldGetWeaponsByCalibre() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByCalibre(13L)

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByCalibre_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectByCalibre(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByCalibre(44L)
        }
    }

    @Test
    fun shouldGetWeaponsByManufacturer() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByManufacturer(14L)

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByManufacturer_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectByManufacturer(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByManufacturer(55L)
        }
    }

    private fun mockSuccessfulOutput() {
        mockWeaponDaoOutput()
        mockWeaponTypeDaoOutput()
        mockCountryDaoOutput()
        mockCalibreDaoOutput()
        mockManufacturerDaoOutput()
    }

    private fun mockWeaponDaoOutput() {
        coEvery {
            mockWeaponDao.selectAll()
        } returns listOf(mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByName(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByYear(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByCountry(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByType(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByCalibre(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByManufacturer(any()) } returns listOf(mockk(relaxed = true))
    }

    private fun mockWeaponTypeDaoOutput() {
        coEvery {
            mockWeaponTypeLocalDataSource.getWeaponTypeById(any())
        } returns WeaponType.BoobyTrap(TYPE_ID)
    }

    private fun mockCountryDaoOutput() {
        coEvery { mockCountryLocalDataSource.getCountryById(any()) } returns mockk(relaxed = true)
    }

    private fun mockCalibreDaoOutput() {
        coEvery { mockCalibreLocalDataSource.getCalibreById(any()) } returns mockk(relaxed = true)
    }

    private fun mockManufacturerDaoOutput() {
        coEvery {
            mockManufacturerLocalDataSource.getManufacturerById(any())
        } returns mockk(relaxed = true)
    }

    private companion object {
        const val TYPE_ID = 111L
    }

}