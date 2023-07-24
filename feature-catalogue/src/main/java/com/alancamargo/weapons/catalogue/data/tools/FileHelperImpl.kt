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
            filesInRelativePath.map { "$relativePath/$it" }
        }
    }

    private suspend fun getRelativePath(weaponName: String): String {
        val country = weaponDao.getCountryByWeaponName(weaponName)
        val countryName = country?.name ?: "Unknown"
        val formattedWeaponName = weaponName.replace("/", "-")
            .replace("\"", "")

        return "$countryName/$formattedWeaponName}"
    }
}
