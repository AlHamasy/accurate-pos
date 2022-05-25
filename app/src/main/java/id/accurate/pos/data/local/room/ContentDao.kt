package id.accurate.pos.data.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.accurate.pos.data.local.entity.CityEntity
import id.accurate.pos.data.local.entity.UserEntity

@Dao
interface ContentDao {

    @Query("SELECT * FROM `user-entity`")
    fun getUsers() : DataSource.Factory<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users : List<UserEntity>)

    @Query("SELECT * FROM `city-entity`")
    fun getCities() : DataSource.Factory<Int, CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities : List<CityEntity>)

}