package com.alancamargo.weapons.catalogue.data.tools

internal interface FileHelper {

    suspend fun getImageFilePaths(weaponId: Long): List<String>
}
