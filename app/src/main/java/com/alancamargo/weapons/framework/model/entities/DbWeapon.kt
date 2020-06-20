package com.alancamargo.weapons.framework.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alancamargo.weapons.framework.model.entities.DbWeapon.Companion.COLUMN_COUNTRY_ID
import com.alancamargo.weapons.framework.model.entities.DbWeapon.Companion.COLUMN_TYPE_ID

@Entity(
    tableName = "Weapon",
    foreignKeys = [
        ForeignKey(
            entity = DbCountry::class,
            parentColumns = [DbCountry.COLUMN_ID],
            childColumns = [COLUMN_COUNTRY_ID]
        ),
        ForeignKey(
            entity = DbWeaponType::class,
            parentColumns = [DbWeaponType.COLUMN_ID],
            childColumns = [COLUMN_TYPE_ID]
        )
    ]
)
data class DbWeapon(
    @PrimaryKey val id: Long,
    val name: String,
    val year: Int,
    val manufacturer: String,
    val countryId: Long,
    val typeId: Long,
    val length: Float,
    val weight: Float,
    val calibre: String,
    val capacity: Int,
    val rateOfFire: Int,
    val accuracy: Int,
    val photos: String
) {

    companion object {
        const val COLUMN_COUNTRY_ID = "countryId"
        const val COLUMN_TYPE_ID = "typeId"
    }

}