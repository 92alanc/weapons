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
            mockFileHelper.getImageFilePaths(weaponName = any())
        } returns listOf(
            "photo1.jpg",
            "photo2.jpg",
            "photo3.jpg"
        )
    }

    @Test
    fun `getAllWeapons should return all weapons`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // WHEN
        val actual = localDataSource.getAllWeapons()

        // THEN
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `filterWeaponsByName should return weapons filtered by name`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        val name = "weapon"
        coEvery { mockDao.getWeaponsByName(name) } returns weapons

        // WHEN
        val actual = localDataSource.filterWeaponsByName(name)

        // THEN
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByYear should return weapons grouped by year`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // WHEN
        val actual = localDataSource.groupWeaponsByYear()

        // THEN
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByCountry should return weapons grouped by country`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // WHEN
        val actual = localDataSource.groupWeaponsByCountry()

        // THEN
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByType should return weapons grouped by type`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // WHEN
        val actual = localDataSource.groupWeaponsByType()

        // THEN
        val expectedEntryCount = 3
        assertThat(actual.entries).hasSize(expectedEntryCount)
        val expectedWeaponCount = 1
        assertThat(actual.values.all { it.size == expectedWeaponCount }).isTrue()
    }

    @Test
    fun `groupWeaponsByCalibre should return weapons grouped by calibre`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // WHEN
        val actual = localDataSource.groupWeaponsByCalibre()

        // THEN
        actual.assertMapHasNullKeyAndValuesAreCorrect()
    }

    @Test
    fun `groupWeaponsByMake should return weapons grouped by make`() = runTest {
        // GIVEN
        val weapons = stubDbWeaponList()
        coEvery { mockDao.getAllWeapons() } returns weapons

        // WHEN
        val actual = localDataSource.groupWeaponsByMake()

        // THEN
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
