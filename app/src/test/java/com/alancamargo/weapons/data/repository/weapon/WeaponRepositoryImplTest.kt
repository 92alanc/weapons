package com.alancamargo.weapons.data.repository.weapon

import com.alancamargo.weapons.data.crash.CrashReportHelper
import com.alancamargo.weapons.data.io.IoHelper
import com.alancamargo.weapons.data.io.Result
import com.alancamargo.weapons.data.local.WeaponLocalDataSource
import com.alancamargo.weapons.domain.entities.*
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class WeaponRepositoryImplTest {

    @MockK lateinit var mockLocalDataSource: WeaponLocalDataSource
    @MockK lateinit var mockCrashReportHelper: CrashReportHelper

    private lateinit var repository: WeaponRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        val ioHelper = IoHelper(mockCrashReportHelper)
        repository = WeaponRepositoryImpl(mockLocalDataSource, ioHelper)
    }

    @Test
    fun shouldGetWeapons() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeapons()
        } returns mapOf(null to listOf(mockk(), mockk(), mockk()))

        val result = repository.getWeapons()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.values.first().size).isEqualTo(3)
    }

    @Test
    fun getWeapons_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeapons() } throws exception

        repository.getWeapons()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeapons_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeapons() } throws IOException()

        val result = repository.getWeapons()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByName() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeaponsByName(any())
        } returns mapOf(null to listOf(mockk(), mockk()))

        val result = repository.getWeaponsByName("AK-47")

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.values.first().size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByName_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByName(any()) } throws exception

        repository.getWeaponsByName("AK-47")

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByName_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByName(any()) } throws IOException()

        val result = repository.getWeaponsByName("AK-47")

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByYear() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeaponsByYear()
        } returns mapOf(
            Year(1, 1947) to listOf(mockk()),
            Year(2, 1949) to listOf(mockk())
        )

        val result = repository.getWeaponsByYear()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByYear_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByYear() } throws exception

        repository.getWeaponsByYear()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByYear_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByYear() } throws IOException()

        val result = repository.getWeaponsByYear()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByCountry() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeaponsByCountry()
        } returns mapOf(
            Country(1, "British Empire", "flag_uk") to listOf(mockk()),
            Country(1, "Russian Empire", "flag_russia") to listOf(mockk())
        )

        val result = repository.getWeaponsByCountry()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByCountry_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByCountry() } throws exception

        repository.getWeaponsByCountry()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByCountry_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByCountry() } throws IOException()

        val result = repository.getWeaponsByCountry()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByType() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeaponsByType()
        } returns mapOf(
            WeaponType.Rifle(1, WeaponType.Rifle.Category.BOLT_ACTION) to listOf(mockk()),
            WeaponType.Pistol(2) to listOf(mockk())
        )

        val result = repository.getWeaponsByType()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByType_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByType() } throws exception

        repository.getWeaponsByType()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByType_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByType() } throws IOException()

        val result = repository.getWeaponsByType()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByCalibre() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeaponsByCalibre()
        } returns mapOf(
            Calibre(1, ".303 British") to listOf(mockk()),
            Calibre(2, "5.56mm") to listOf(mockk())
        )

        val result = repository.getWeaponsByCalibre()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByCalibre_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByCalibre() } throws exception

        repository.getWeaponsByCalibre()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByCalibre_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByCalibre() } throws IOException()

        val result = repository.getWeaponsByCalibre()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

    @Test
    fun shouldGetWeaponsByManufacturer() = runBlocking {
        coEvery {
            mockLocalDataSource.getWeaponsByManufacturer()
        } returns mapOf(
            Manufacturer(1, "DWM") to listOf(mockk()),
            Manufacturer(2, "Steyr-Mannlicher") to listOf(mockk())
        )

        val result = repository.getWeaponsByManufacturer()

        assertThat(result).isInstanceOf(Result.Success::class.java)
        require(result is Result.Success)
        assertThat(result.body.size).isEqualTo(2)
    }

    @Test
    fun getWeaponsByManufacturer_localDataSourceThrowsException_shouldLogToCrashReport() = runBlocking {
        val exception = IOException()
        coEvery { mockLocalDataSource.getWeaponsByManufacturer() } throws exception

        repository.getWeaponsByManufacturer()

        verify { mockCrashReportHelper.log(exception) }
    }

    @Test
    fun getWeaponsByManufacturer_localDataSourceThrowsException_shouldReturnError() = runBlocking {
        coEvery { mockLocalDataSource.getWeaponsByManufacturer() } throws IOException()

        val result = repository.getWeaponsByManufacturer()

        assertThat(result).isInstanceOf(Result.Error::class.java)
    }

}