package com.alancamargo.weapons.framework.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alancamargo.weapons.framework.model.entities.DbWeapon.Companion.COLUMN_CALIBRE_ID
import com.alancamargo.weapons.framework.model.entities.DbWeapon.Companion.COLUMN_COUNTRY_ID
import com.alancamargo.weapons.framework.model.entities.DbWeapon.Companion.COLUMN_TYPE_ID

@Entity(
    tableName = "WEAPON",
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
        ),
        ForeignKey(
            entity = DbCalibre::class,
            parentColumns = [DbCalibre.COLUMN_ID],
            childColumns = [COLUMN_CALIBRE_ID]
        )
    ]
)
data class DbWeapon(
    @PrimaryKey val id: Long,
    val name: String,
    val year: Int,
    val manufacturer: String,
    @ColumnInfo(name = COLUMN_COUNTRY_ID) val countryId: Long,
    @ColumnInfo(name = COLUMN_TYPE_ID) val typeId: Long,
    val length: Float,
    val weight: Float,
    @ColumnInfo(name = COLUMN_CALIBRE_ID) val calibreId: Long,
    val capacity: Int,
    val rateOfFire: Int,
    val accuracy: Int,
    val photosJson: String
) {

    companion object {
        const val COLUMN_COUNTRY_ID = "COUNTRY_ID"
        const val COLUMN_TYPE_ID = "TYPE_ID"
        const val COLUMN_CALIBRE_ID = "CALIBRE_ID"
    }

}