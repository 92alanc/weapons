package com.alancamargo.weapons.framework.local

import com.alancamargo.weapons.framework.db.WeaponTypeDao
import com.alancamargo.weapons.framework.model.entities.DbWeaponType
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class WeaponTypeLocalDataSourceImplTest {

    @MockK lateinit var mockWeaponTypeDao: WeaponTypeDao

    private lateinit var localDataSource: WeaponTypeLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        localDataSource = WeaponTypeLocalDataSourceImpl(mockWeaponTypeDao)
    }

    @Test
    fun shouldGetWeaponTypes() = runBlocking {
        val data = listOf(
            DbWeaponType(1L, DbWeaponType.NAME_PISTOL, category = null),
            DbWeaponType(2L, DbWeaponType.NAME_CARBINE, category = null)
        )
        coEvery {
            mockWeaponTypeDao.selectAll()
        } returns data

        val weaponTypes = localDataSource.getWeaponTypes()

        assertThat(weaponTypes.size).isEqualTo(2)
    }

    @Test(expected = IOException::class)
    fun getWeaponTypes_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponTypeDao.selectAll() } throws IOException()

        runBlocking {
            localDataSource.getWeaponTypes()
        }
    }

    @Test
    fun shouldGetWeaponTypeById() = runBlocking {
        coEvery {
            mockWeaponTypeDao.selectById(any())
        } returns DbWeaponType(1L, DbWeaponType.NAME_PISTOL, category = null)

        val weaponType = localDataSource.getWeaponTypeById(1L)

        assertThat(weaponType).isNotNull()
    }

    @Test(expected = IOException::class)
    fun getWeaponTypeById_databaseThrowsException_shouldThrow() {
        coEvery { mockWeaponTypeDao.selectById(any()) } throws IOException()

        runBlocking {
            localDataSource.getWeaponTypeById(1L)
        }
    }

}