package com.alancamargo.weapons.framework.tools

import android.content.Context
import com.alancamargo.weapons.framework.db.CountryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException

class FileHelperImpl(
    private val context: Context,
    private val countryDao: CountryDao
) : FileHelper {

    override suspend fun getImageFilePaths(weaponName: String): List<String> {
        return withContext(Dispatchers.IO) {
            val relativePath = getRelativePath(weaponName)
            val filesInRelativePath = context.assets.list(relativePath)

            if (filesInRelativePath.isNullOrEmpty())
                throw FileNotFoundException()
            else
                filesInRelativePath.map { "$relativePath/$it" }
        }
    }

    private suspend fun getRelativePath(weaponName: String): String = withContext(Dispatchers.IO) {
        val country = countryDao.getCountryByWeaponName(weaponName)
        val countryName = country?.name ?: "Unknown"

        "$countryName/${weaponName.replace("/", "-").replace("\"", "")}"
    }
}
