package com.alancamargo.weapons.catalogue.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon.Companion.COLUMN_CALIBRE_ID
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon.Companion.COLUMN_COUNTRY_ID
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon.Companion.COLUMN_MANUFACTURER_ID
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon.Companion.COLUMN_TYPE_ID
import com.alancamargo.weapons.catalogue.data.model.RawDbWeapon.Companion.COLUMN_YEAR_ID

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
internal data class RawDbWeapon(
    @PrimaryKey val id: Long,
    val name: String,
    val yearId: Long?,
    val manufacturerId: Long?,
    val countryId: Long?,
    val typeId: Long,
    val lengthInMm: Int?,
    val massInKg: Float?,
    val calibreId: Long?,
    val capacityInRounds: Int?,
    val rateOfFireInRpm: Int?,
    val effectiveRangeInM: Int?
) {

    companion object {
        const val COLUMN_MANUFACTURER_ID = "manufacturer_id"
        const val COLUMN_COUNTRY_ID = "country_id"
        const val COLUMN_TYPE_ID = "type_id"
        const val COLUMN_CALIBRE_ID = "calibre_id"
        const val COLUMN_YEAR_ID = "year_id"
    }
}
