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

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getImageFilePaths(weaponName: String): List<String> {
        val relativePath = getRelativePath(weaponName)

        val assets = withContext(Dispatchers.IO) {
            context.assets.list(relativePath)
        }

        if (assets.isNullOrEmpty())
            throw FileNotFoundException()
        else
            return assets.map { "$relativePath/$it" }
    }

    private suspend fun getRelativePath(weaponName: String): String {
        val countryName = withContext(Dispatchers.IO) {
            countryDao.getCountryByWeaponName(weaponName).name
        }

        return "$countryName/$weaponName"
    }

}