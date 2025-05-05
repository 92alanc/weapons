package com.alancamargo.weapons.catalogue.domain.usecase

import app.cash.turbine.test
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.domain.model.WeaponQuery
import com.alancamargo.weapons.catalogue.domain.repository.WeaponRepository
import com.alancamargo.weapons.catalogue.testtools.stubWeaponListMap
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
        // Given
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.getAllWeapons() } returns expected

        // When
        val result = useCase(WeaponQuery.All)

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByCalibre invoke should return correct result`() = runTest {
        // Given
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.groupWeaponsByCalibre() } returns expected

        // When
        val result = useCase(WeaponQuery.ByCalibre)

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByCountry invoke should return correct result`() = runTest {
        // Given
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.groupWeaponsByCountry() } returns expected

        // When
        val result = useCase(WeaponQuery.ByCountry)

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByMake invoke should return correct result`() = runTest {
        // Given
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.groupWeaponsByMake() } returns expected

        // When
        val result = useCase(WeaponQuery.ByMake)

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByType invoke should return correct result`() = runTest {
        // Given
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.groupWeaponsByType() } returns expected

        // When
        val result = useCase(WeaponQuery.ByType)

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByYear invoke should return correct result`() = runTest {
        // Given
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.groupWeaponsByYear() } returns expected

        // When
        val result = useCase(WeaponQuery.ByYear)

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `when query is ByName invoke should return correct result`() = runTest {
        // Given
        val name = "weapon"
        val expected = WeaponListResult.Success(stubWeaponListMap())
        coEvery { mockRepository.filterWeaponsByName(name) } returns expected

        // When
        val result = useCase(WeaponQuery.ByName(name))

        // Then
        result.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }
}
