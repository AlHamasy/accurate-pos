package id.accurate.pos.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user-entity")
data class UserEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : String,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "address")
    var address : String,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber : String,

    @ColumnInfo(name = "city")
    var city : String,
)
