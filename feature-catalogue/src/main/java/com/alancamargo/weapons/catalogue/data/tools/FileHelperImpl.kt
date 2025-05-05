package com.alancamargo.weapons.catalogue.data.tools

import android.content.Context
import java.io.FileNotFoundException
import javax.inject.Inject

internal class FileHelperImpl @Inject constructor(private val context: Context) : FileHelper {

    override suspend fun getImageFilePaths(weaponId: Long): List<String> {
        val filesInRelativePath = context.assets.list(weaponId.toString())

        return if (filesInRelativePath.isNullOrEmpty()) {
            throw FileNotFoundException()
        } else {
            filesInRelativePath.map { "file:///android_asset/$weaponId/$it" }
        }
    }
}
