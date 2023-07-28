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
        // GIVEN
        val expected = stubWeaponListMap()
        coEvery { mockLocalDataSource.getAllWeapons() } returns expected

        // WHEN
        val actual = repository.getAllWeapons()

        // THEN
        assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
    }

    @Test
    fun `when local data source throws exception getAllWeapons should log error`() = runTest {
        // GIVEN
        val exception = Throwable()
        coEvery { mockLocalDataSource.getAllWeapons() } throws exception

        // WHEN
        repository.getAllWeapons()

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception getAllWeapons should return Error`() = runTest {
        // GIVEN
        val exception = Throwable()
        coEvery { mockLocalDataSource.getAllWeapons() } throws exception

        // WHEN
        val actual = repository.getAllWeapons()

        // THEN
        assertThat(actual).isEqualTo(WeaponListResult.Error)
    }

    @Test
    fun `when local data source returns weapons filterWeaponsByName should return Success`() {
        runTest {
            // GIVEN
            val name = "weapon"
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.filterWeaponsByName(name) } returns expected

            // WHEN
            val actual = repository.filterWeaponsByName(name)

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception filterWeaponsByName should log error`() = runTest {
        // GIVEN
        val name = "weapon"
        val exception = Throwable()
        coEvery { mockLocalDataSource.filterWeaponsByName(name) } throws exception

        // WHEN
        repository.filterWeaponsByName(name)

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception filterWeaponsByName should return Error`() {
        runTest {
            // GIVEN
            val name = "weapon"
            val exception = Throwable()
            coEvery { mockLocalDataSource.filterWeaponsByName(name) } throws exception

            // WHEN
            val actual = repository.filterWeaponsByName(name)

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByYear should return Success`() {
        runTest {
            // GIVEN
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByYear() } returns expected

            // WHEN
            val actual = repository.groupWeaponsByYear()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByYear should log error`() = runTest {
        // GIVEN
        val exception = Throwable()
        coEvery { mockLocalDataSource.groupWeaponsByYear() } throws exception

        // WHEN
        repository.groupWeaponsByYear()

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByYear should return Error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByYear() } throws exception

            // WHEN
            val actual = repository.groupWeaponsByYear()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByCountry should return Success`() {
        runTest {
            // GIVEN
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByCountry() } returns expected

            // WHEN
            val actual = repository.groupWeaponsByCountry()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCountry should log error`() = runTest {
        // GIVEN
        val exception = Throwable()
        coEvery { mockLocalDataSource.groupWeaponsByCountry() } throws exception

        // WHEN
        repository.groupWeaponsByCountry()

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCountry should return Error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByCountry() } throws exception

            // WHEN
            val actual = repository.groupWeaponsByCountry()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByType should return Success`() {
        runTest {
            // GIVEN
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByType() } returns expected

            // WHEN
            val actual = repository.groupWeaponsByType()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByType should log error`() = runTest {
        // GIVEN
        val exception = Throwable()
        coEvery { mockLocalDataSource.groupWeaponsByType() } throws exception

        // WHEN
        repository.groupWeaponsByType()

        // THEN
        verify { mockLogger.error(exception) }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByType should return Error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByType() } throws exception

            // WHEN
            val actual = repository.groupWeaponsByType()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByCalibre should return Success`() {
        runTest {
            // GIVEN
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByCalibre() } returns expected

            // WHEN
            val actual = repository.groupWeaponsByCalibre()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCalibre should log error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByCalibre() } throws exception

            // WHEN
            repository.groupWeaponsByCalibre()

            // THEN
            verify { mockLogger.error(exception) }
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByCalibre should return Error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByCalibre() } throws exception

            // WHEN
            val actual = repository.groupWeaponsByCalibre()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }

    @Test
    fun `when local data source returns weapons groupWeaponsByManufacturer should return Success`() {
        runTest {
            // GIVEN
            val expected = stubWeaponListMap()
            coEvery { mockLocalDataSource.groupWeaponsByManufacturer() } returns expected

            // WHEN
            val actual = repository.groupWeaponsByManufacturer()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Success(expected))
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByManufacturer should log error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByManufacturer() } throws exception

            // WHEN
            repository.groupWeaponsByManufacturer()

            // THEN
            verify { mockLogger.error(exception) }
        }
    }

    @Test
    fun `when local data source throws exception groupWeaponsByManufacturer should return Error`() {
        runTest {
            // GIVEN
            val exception = Throwable()
            coEvery { mockLocalDataSource.groupWeaponsByManufacturer() } throws exception

            // WHEN
            val actual = repository.groupWeaponsByManufacturer()

            // THEN
            assertThat(actual).isEqualTo(WeaponListResult.Error)
        }
    }
}
