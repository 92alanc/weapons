package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.*
import com.alancamargo.weapons.framework.model.entities.DbWeaponType
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
    @MockK lateinit var mockWeaponTypeDao: WeaponTypeDao
    @MockK lateinit var mockCountryDao: CountryDao
    @MockK lateinit var mockCalibreDao: CalibreDao
    @MockK lateinit var mockManufacturerDao: ManufacturerDao

    private lateinit var localDataSource: WeaponLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localDataSource = WeaponLocalDataSourceImpl(
            mockWeaponDao,
            mockWeaponTypeDao,
            mockCountryDao,
            mockCalibreDao,
            mockManufacturerDao
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
    fun shouldGetWeaponById() = runBlocking {
        mockSuccessfulOutput()

        val weapon = localDataSource.getWeaponById(123L)

        assertThat(weapon).isNotNull()
    }

    @Test(expected = IOException::class)
    fun getWeaponById_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectById(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponById(111L)
        }
    }

    @Test
    fun shouldGetWeaponsByName() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByName("M16A1")

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByName_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectByName(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByName("MP40")
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
        coEvery { mockWeaponDao.selectById(any()) } returns mockk(relaxed = true)
        coEvery { mockWeaponDao.selectByName(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByYear(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByCountry(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByType(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByCalibre(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByManufacturer(any()) } returns listOf(mockk(relaxed = true))
    }

    private fun mockWeaponTypeDaoOutput() {
        coEvery {
            mockWeaponTypeDao.selectById(any())
        } returns DbWeaponType(TYPE_ID, DbWeaponType.NAME_BOOBY_TRAP, category = null)
    }

    private fun mockCountryDaoOutput() {
        coEvery { mockCountryDao.selectById(any()) } returns mockk(relaxed = true)
    }

    private fun mockCalibreDaoOutput() {
        coEvery { mockCalibreDao.selectById(any()) } returns mockk(relaxed = true)
    }

    private fun mockManufacturerDaoOutput() {
        coEvery { mockManufacturerDao.selectById(any()) } returns mockk(relaxed = true)
    }

    private companion object {
        const val TYPE_ID = 111L
    }

}