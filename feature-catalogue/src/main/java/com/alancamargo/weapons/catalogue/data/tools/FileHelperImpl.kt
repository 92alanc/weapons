package com.alancamargo.weapons.catalogue.data.tools

import android.content.Context
import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import java.io.FileNotFoundException
import javax.inject.Inject

internal class FileHelperImpl @Inject constructor(
    private val context: Context,
    private val weaponDao: WeaponDao
) : FileHelper {

    override suspend fun getImageFilePaths(weaponName: String): List<String> {
        val relativePath = getRelativePath(weaponName)
        val filesInRelativePath = context.assets.list(relativePath)

        return if (filesInRelativePath.isNullOrEmpty()) {
            throw FileNotFoundException()
        } else {
            filesInRelativePath.map { "file:///android_asset/$relativePath/$it" }
        }
    }

    private suspend fun getRelativePath(weaponName: String): String {
        val country = weaponDao.getCountryByWeaponName(weaponName)
        val countryToken = country?.token ?: "unknown"
        val formattedCountryToken = if (countryToken.contains("-")) {
            countryToken
        } else {
            countryToken.replaceFirstChar { it.uppercase() }
        }
        val formattedWeaponName = weaponName.replace("/", "-")
            .replace("\"", "")

        return "$formattedCountryToken/$formattedWeaponName"
    }
}
