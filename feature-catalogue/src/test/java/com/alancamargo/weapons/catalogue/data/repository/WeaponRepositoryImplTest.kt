package com.alancamargo.weapons.catalogue.data.repository

import com.alancamargo.weapons.catalogue.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.catalogue.domain.model.WeaponListResult
import com.alancamargo.weapons.catalogue.testtools.stubWeaponListMap
import com.alancamargo.weapons.core.log.Logger
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test

class WeaponRepositoryImplTest {

    private val mockLocalDataSource = mockk<WeaponLocalDataSource>()
    private val mockLogger = mockk<Logger>(relaxed = true)

    private val repository = WeaponRepositoryImpl(
        mockLocalDataSource,
        mockLogger
    )

    @Test
    fun `when local data source returns weapons getAllWeapons should return Success`() = runTest {
        // Given
        val expected = stubWeaponListMap()
        coEvery { mockLocalDataSource.getAllWeapons() } returns expected

        // When
        val actual = repository.getAllWeapons()

        // Then
        assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
    }

    @Test
    fun `when local data source throws exception getAllWeapons should log error`() = runTest {
        // Given
        val exception = Throwable()
        coEvery { mockLocalDataSource.getAllWeapons() } throws exception

        // When
        repository.getAllWeapons()

        // Then
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception getAllWeapons should return Error`() = runTest {
        // Given
        val exception = Throwable()
        coEvery { mockLocalDataSource.getAllWeapons() } throws exception

        // When
        val actual = repository.getAllWeapons()

        // Then
        assertThat(actual).isEqualTo(WeaponListResult.Error)
    }

    @Test
    fun `when local data source returns weapons filterWeaponsByName should return Success`() {
        runTest {
            // Given
            val name = "weapon"
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.filterWeaponsByName(name) } returns expected

            // When
            val actual = repository.filterWeaponsByName(name)

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception filterWeaponsByName should log error`() = runTest {
        // Given
        val name = "weapon"
        val exception = Throwable()
        coEvery { mockLocalDataSource.filterWeaponsByName(name) } throws exception

        // When
        repository.filterWeaponsByName(name)

        // Then
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception filterWeaponsByName should return Error`() {
        runTest {
            // Given
            val name = "weapon"
            val exception = Throwable()
            coEvery { mockLocalDataSource.filterWeaponsByName(name) } throws exception

            // When
            val actual = repository.filterWeaponsByName(name)

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByYear should return Success`() {
        runTest {
            // Given
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByYear() } returns expected

            // When
            val actual = repository.groupWeaponsByYear()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByYear should log error`() = runTest {
        // Given
        val exception = Throwable()
        coEvery { mockLocalDataSource.groupWeaponsByYear() } throws exception

        // When
        repository.groupWeaponsByYear()

        // Then
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByYear should return Error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByYear() } throws exception

            // When
            val actual = repository.groupWeaponsByYear()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByCountry should return Success`() {
        runTest {
            // Given
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByCountry() } returns expected

            // When
            val actual = repository.groupWeaponsByCountry()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCountry should log error`() = runTest {
        // Given
        val exception = Throwable()
        coEvery { mockLocalDataSource.groupWeaponsByCountry() } throws exception

        // When
        repository.groupWeaponsByCountry()

        // Then
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCountry should return Error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByCountry() } throws exception

            // When
            val actual = repository.groupWeaponsByCountry()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByType should return Success`() {
        runTest {
            // Given
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByType() } returns expected

            // When
            val actual = repository.groupWeaponsByType()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByType should log error`() = runTest {
        // Given
        val exception = Throwable()
        coEvery { mockLocalDataSource.groupWeaponsByType() } throws exception

        // When
        repository.groupWeaponsByType()

        // Then
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByType should return Error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByType() } throws exception

            // When
            val actual = repository.groupWeaponsByType()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByCalibre should return Success`() {
        runTest {
            // Given
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByCalibre() } returns expected

            // When
            val actual = repository.groupWeaponsByCalibre()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCalibre should log error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByCalibre() } throws exception

            // When
            repository.groupWeaponsByCalibre()

            // Then
            verify { mockLogger.error(exception) }
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCalibre should return Error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByCalibre() } throws exception

            // When
            val actual = repository.groupWeaponsByCalibre()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByMake should return Success`() {
        runTest {
            // Given
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByMake() } returns expected

            // When
            val actual = repository.groupWeaponsByMake()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByMake should log error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByMake() } throws exception

            // When
            repository.groupWeaponsByMake()

            // Then
            verify { mockLogger.error(exception) }
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByMake should return Error`() {
        runTest {
            // Given
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByMake() } throws exception

            // When
            val actual = repository.groupWeaponsByMake()

            // Then
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }
}
