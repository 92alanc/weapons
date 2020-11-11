package com.alancamargo.weapons.framework.tools

interface FileHelper {

    suspend fun getImageFilePaths(weaponName: String): List<String>

}