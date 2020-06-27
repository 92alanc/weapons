package com.alancamargo.weapons.framework.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alancamargo.weapons.framework.entities.DbWeapon.Companion.COLUMN_CALIBRE_ID
import com.alancamargo.weapons.framework.entities.DbWeapon.Companion.COLUMN_COUNTRY_ID
import com.alancamargo.weapons.framework.entities.DbWeapon.Companion.COLUMN_MANUFACTURER_ID
import com.alancamargo.weapons.framework.entities.DbWeapon.Companion.COLUMN_TYPE_ID
import com.alancamargo.weapons.framework.entities.DbWeapon.Companion.COLUMN_YEAR_ID

@Entity(
    tableName = "WEAPON",
    foreignKeys = [
        ForeignKey(
            entity = DbManufacturer::class,
            parentColumns = [DbManufacturer.COLUMN_ID],
            childColumns = [COLUMN_MANUFACTURER_ID]
        ),
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
        ),
        ForeignKey(
            entity = DbYear::class,
            parentColumns = [DbYear.COLUMN_ID],
            childColumns = [COLUMN_YEAR_ID]
        )
    ]
)
data class DbWeapon(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = COLUMN_YEAR_ID) val yearId: Long,
    @ColumnInfo(name = COLUMN_MANUFACTURER_ID) val manufacturerId: Long,
    @ColumnInfo(name = COLUMN_COUNTRY_ID) val countryId: Long,
    @ColumnInfo(name = COLUMN_TYPE_ID) val typeId: Long,
    val length: Float,
    val weight: Float,
    @ColumnInfo(name = COLUMN_CALIBRE_ID) val calibreId: Long,
    val capacity: Int,
    @ColumnInfo(name = COLUMN_RATE_OF_FIRE) val rateOfFire: Int,
    val accuracy: Int,
    @ColumnInfo(name = COLUMN_PHOTOS_JSON) val photosJson: String
) {

    companion object {
        const val COLUMN_MANUFACTURER_ID = "manufacturer_id"
        const val COLUMN_COUNTRY_ID = "country_id"
        const val COLUMN_TYPE_ID = "type_id"
        const val COLUMN_CALIBRE_ID = "calibre_id"
        const val COLUMN_YEAR_ID = "year_id"
        const val COLUMN_RATE_OF_FIRE = "rate_of_fire"
        const val COLUMN_PHOTOS_JSON = "photos_json"
    }

}