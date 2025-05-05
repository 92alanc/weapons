package com.alancamargo.weapons.catalogue.data.local

import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import com.alancamargo.weapons.catalogue.data.tools.FileHelper
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.testtools.stubDbWeaponList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeaponLocalDataSourceImplTest {

    private val mockDao = mockk<WeaponDao>()
    private val mockFileHelper = mockk<FileHelper>()

    private val localDataSource = WeaponLocalDataSourceImpl(mockDao, mockFileHelper)

    @Before
    fun setUp() {
        coEvery {
            mockFileHelper.getImageFilePaths(weaponId = any())
        } returns listOf(
            "photo1.jpg",
            "photo2.jpg",
            "photo3.jpg"
        )
    }

    @Test
    fun `getAllWeapons should return all weapons`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // When
        val actual = localDataSource.getAllWeapons()

        // Then
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `filterWeaponsByName should return weapons filtered by name`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        val name = "weapon"
        coEvery { mockDao.getWeaponsByName(name) } returns weapons

        // When
        val actual = localDataSource.filterWeaponsByName(name)

        // Then
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByYear should return weapons grouped by year`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // When
        val actual = localDataSource.groupWeaponsByYear()

        // Then
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByCountry should return weapons grouped by country`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // When
        val actual = localDataSource.groupWeaponsByCountry()

        // Then
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByType should return weapons grouped by type`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // When
        val actual = localDataSource.groupWeaponsByType()

        // Then
        val expectedEntryCount = 1
        assertThat(actual.entries).hasSize(expectedEntryCount)
        val expectedWeaponCount = 3
        assertThat(actual.values.all { it.size == expectedWeaponCount }).isTrue()
    }

    @Test
    fun `groupWeaponsByCalibre should return weapons grouped by calibre`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // When
        val actual = localDataSource.groupWeaponsByCalibre()

        // Then
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByMake should return weapons grouped by make`() = runTest {
        // Given
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // When
        val actual = localDataSource.groupWeaponsByMake()

        // Then
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    private fun Map<WeaponListHeader?, List<Weapon>>.assertMapHasNullKeyAndValuesAreCorrect() {
        val expectedEntryCount = 1
        assertThat(entries).hasSize(expectedEntryCount)
        assertThat(keys.all { it == null }).isTrue()
        val expectedWeaponCount = 3
        assertThat(values.first()).hasSize(expectedWeaponCount)
    }
}
