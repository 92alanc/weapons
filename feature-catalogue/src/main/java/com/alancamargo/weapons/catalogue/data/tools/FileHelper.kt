package com.alancamargo.weapons.catalogue.data.tools

internal interface FileHelper {

    suspend fun getImageFilePaths(weaponName: String): List<String>
}
