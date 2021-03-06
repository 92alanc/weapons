package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.data.local.*
import com.alancamargo.weapons.di.DB_WEAPON_MAPPER
import com.alancamargo.weapons.domain.entities.Weapon
import com.alancamargo.weapons.domain.entities.WeaponType
import com.alancamargo.weapons.domain.mapper.EntityMapper
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.entities.DbWeapon
import com.alancamargo.weapons.framework.tools.FileHelper
import com.alancamargo.weapons.util.PHOTO
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.IOException

class WeaponLocalDataSourceImplTest {

    @MockK lateinit var mockWeaponDao: WeaponDao
    @MockK lateinit var mockWeaponTypeLocalDataSource: WeaponTypeLocalDataSource
    @MockK lateinit var mockCountryLocalDataSource: CountryLocalDataSource
    @MockK lateinit var mockCalibreLocalDataSource: CalibreLocalDataSource
    @MockK lateinit var mockManufacturerLocalDataSource: ManufacturerLocalDataSource
    @MockK lateinit var mockYearLocalDataSource: YearLocalDataSource
    @MockK lateinit var mockFileHelper: FileHelper

    private lateinit var localDataSource: WeaponLocalDataSourceImpl

    @Before
    fun setUp() {
        initialiseKoin()
        MockKAnnotations.init(this)

        coEvery { mockFileHelper.getImageFilePaths(any()) } returns listOf(PHOTO)

        localDataSource = WeaponLocalDataSourceImpl(
            mockWeaponDao,
            mockWeaponTypeLocalDataSource,
            mockCountryLocalDataSource,
            mockCalibreLocalDataSource,
            mockManufacturerLocalDataSource,
            mockYearLocalDataSource,
            mockFileHelper
        )
    }

    @Test
    fun shouldGetWeapons() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeapons()

        assertThat(weapons.values.first().size).isEqualTo(1)
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

        val weapons = localDataSource.getWeaponsByYear()

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByYear_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByYear()
        }
    }

    @Test
    fun shouldGetWeaponsByCountry() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByCountry()

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByCountry_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByCountry()
        }
    }

    @Test
    fun shouldGetWeaponsByType() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByType()

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByType_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByType()
        }
    }

    @Test
    fun shouldGetWeaponsByCalibre() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByCalibre()

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByCalibre_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByCalibre()
        }
    }

    @Test
    fun shouldGetWeaponsByManufacturer() = runBlocking {
        mockSuccessfulOutput()

        val weapons = localDataSource.getWeaponsByManufacturer()

        assertThat(weapons.size).isEqualTo(1)
    }

    @Test(expected = IOException::class)
    fun getWeaponsByManufacturer_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeaponsByManufacturer()
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private fun initialiseKoin() {
        startKoin {
            loadKoinModules(module {
                factory(named(DB_WEAPON_MAPPER)) { mockDbWeaponMapper() }
            })
        }
    }

    private fun mockDbWeaponMapper() = mockk<EntityMapper<DbWeapon, Weapon>>().apply {
        every { map(any()) } returns mockk(relaxed = true)
    }

    private fun mockSuccessfulOutput() {
        mockWeaponLocalDataSourceOutput()
        mockWeaponTypeLocalDataSourceOutput()
        mockCountryLocalDataSourceOutput()
        mockCalibreLocalDataSourceOutput()
        mockManufacturerLocalDataSourceOutput()
        mockYearLocalDataSourceOutput()
    }

    private fun mockWeaponLocalDataSourceOutput() {
        coEvery { mockWeaponDao.selectAll() } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByName(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByYear(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByCountry(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByType(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByCalibre(any()) } returns listOf(mockk(relaxed = true))
        coEvery { mockWeaponDao.selectByManufacturer(any()) } returns listOf(mockk(relaxed = true))
    }

    private fun mockWeaponTypeLocalDataSourceOutput() {
        coEvery {
            mockWeaponTypeLocalDataSource.getWeaponTypeById(any())
        } returns WeaponType.BoobyTrap(TYPE_ID)
    }

    private fun mockCountryLocalDataSourceOutput() {
        coEvery { mockCountryLocalDataSource.getCountryById(any()) } returns mockk(relaxed = true)
    }

    private fun mockCalibreLocalDataSourceOutput() {
        coEvery { mockCalibreLocalDataSource.getCalibreById(any()) } returns mockk(relaxed = true)
    }

    private fun mockManufacturerLocalDataSourceOutput() {
        coEvery {
            mockManufacturerLocalDataSource.getManufacturerById(any())
        } returns mockk(relaxed = true)
    }

    private fun mockYearLocalDataSourceOutput() {
        coEvery { mockYearLocalDataSource.getYearById(any()) } returns mockk(relaxed = true)
    }

    private companion object {
        const val TYPE_ID = 111L
    }

}