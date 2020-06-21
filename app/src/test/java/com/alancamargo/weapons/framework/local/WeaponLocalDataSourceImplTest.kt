package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.CalibreDao
import com.alancamargo.weapons.framework.db.CountryDao
import com.alancamargo.weapons.framework.db.WeaponDao
import com.alancamargo.weapons.framework.db.WeaponTypeDao
import com.alancamargo.weapons.framework.model.entities.DbWeaponType
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeaponLocalDataSourceImplTest {

    @MockK lateinit var mockWeaponDao: WeaponDao
    @MockK lateinit var mockWeaponTypeDao: WeaponTypeDao
    @MockK lateinit var mockCountryDao: CountryDao
    @MockK lateinit var mockCalibreDao: CalibreDao

    private lateinit var localDataSource: WeaponLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localDataSource = WeaponLocalDataSourceImpl(
            mockWeaponDao,
            mockWeaponTypeDao,
            mockCountryDao,
            mockCalibreDao
        )
    }

    @Test
    fun shouldGetWeapons() = runBlocking {
        mockWeaponDaoOutput()
        mockWeaponTypeDaoOutput()
        mockCountryDaoOutput()
        mockCalibreDaoOutput()

        val weapons = localDataSource.getWeapons()

        assertThat(weapons.size).isEqualTo(3)
    }

    private fun mockWeaponDaoOutput() {
        coEvery {
            mockWeaponDao.selectAll()
        } returns listOf(mockk(relaxed = true), mockk(relaxed = true), mockk(relaxed = true))
        coEvery { mockWeaponDao.selectById(any()) } returns mockk(relaxed = true)
        coEvery { mockWeaponDao.selectByName(any()) } returns mockk(relaxed = true)
        coEvery { mockWeaponDao.selectByYear(any()) } returns mockk(relaxed = true)
        coEvery { mockWeaponDao.selectByCountry(any()) } returns mockk(relaxed = true)
        coEvery { mockWeaponDao.selectByType(any()) } returns mockk(relaxed = true)
        coEvery { mockWeaponDao.selectByCalibre(any()) } returns mockk(relaxed = true)
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

    private companion object {
        const val TYPE_ID = 111L
    }

}