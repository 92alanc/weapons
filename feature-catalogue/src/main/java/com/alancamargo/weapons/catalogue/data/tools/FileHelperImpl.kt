package com.alancamargo.weapons.catalogue.data.tools

import android.content.Context
import com.alancamargo.weapons.catalogue.data.db.WeaponDao
import java.io.FileNotFoundException
import javax.inject.Inject

internal class FileHelperImpl @Inject constructor(
    private val context: Context,
    private val weaponDao: WeaponDao
) : FileHelper {

    override suspend fun getImageFilePaths(weaponId: Long): List<String> {
        val relativePath = getRelativePath(weaponId)
        val filesInRelativePath = context.assets.list(relativePath)

        return if (filesInRelativePath.isNullOrEmpty()) {
            throw FileNotFoundException()
        } else {
            filesInRelativePath.map { "file:///android_asset/$relativePath/$it" }
        }
    }

    private suspend fun getRelativePath(weaponId: Long): String {
        val country = weaponDao.getCountryByWeaponId(weaponId)
        val countryToken = country?.token ?: "unknown"
        return "$countryToken/$weaponId"
    }
}
