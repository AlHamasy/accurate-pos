package id.accurate.pos.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city-entity")
data class CityEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : String,

    @ColumnInfo(name = "name")
    var name : String
)