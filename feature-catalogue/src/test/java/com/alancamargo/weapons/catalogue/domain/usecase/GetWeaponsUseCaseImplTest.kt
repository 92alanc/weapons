package com.alancamargo.weapons.catalogue.domain.usecase

import app.cash.turbine.test
import com.alancamargo.weapons.catalogue.domain.model.Weapon
import com.alancamargo.weapons.catalogue.domain.model.WeaponListHeader
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.model.WeaponQuery
import com.alancamargo.weapons.catalogue.domain.repository.WeaponRepository
import com.alancamargo.weapons.catalogue.testtools.stubWeaponList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetWeaponsUseCaseImplTest {

    private val mockRepository = mockk<WeaponRepository>()
    private val useCase = GetWeaponsUseCaseImpl(mockRepository)

    @Test
    fun `when query is All invoke should return correct result`() = runTest {
        // GIVEN
        val expected = getExpectedResult()
        coEvery { mockRepository.getAllWeapons() } returns expected

        // WHEN
        val result = useCase(WeaponQuery.All)

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByCalibre invoke should return correct result`() = runTest {
        // GIVEN
        val expected = getExpectedResult()
        coEvery { mockRepository.groupWeaponsByCalibre() } returns expected

        // WHEN
        val result = useCase(WeaponQuery.ByCalibre)

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByCountry invoke should return correct result`() = runTest {
        // GIVEN
        val expected = getExpectedResult()
        coEvery { mockRepository.groupWeaponsByCountry() } returns expected

        // WHEN
        val result = useCase(WeaponQuery.ByCountry)

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByManufacturer invoke should return correct result`() = runTest {
        // GIVEN
        val expected = getExpectedResult()
        coEvery { mockRepository.groupWeaponsByManufacturer() } returns expected

        // WHEN
        val result = useCase(WeaponQuery.ByManufacturer)

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByType invoke should return correct result`() = runTest {
        // GIVEN
        val expected = getExpectedResult()
        coEvery { mockRepository.groupWeaponsByType() } returns expected

        // WHEN
        val result = useCase(WeaponQuery.ByType)

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByYear invoke should return correct result`() = runTest {
        // GIVEN
        val expected = getExpectedResult()
        coEvery { mockRepository.groupWeaponsByYear() } returns expected

        // WHEN
        val result = useCase(WeaponQuery.ByYear)

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByName invoke should return correct result`() = runTest {
        // GIVEN
        val name = "weapon"
        val expected = getExpectedResult()
        coEvery { mockRepository.filterWeaponsByName(name) } returns expected

        // WHEN
        val result = useCase(WeaponQuery.ByName(name))

        // THEN
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    private fun getExpectedResult(): WeaponListResult {
        val weapons = mapOf<WeaponListHeader?, List<Weapon>>(null to stubWeaponList())
        return WeaponListResult.Success(weapons)
    }
}
