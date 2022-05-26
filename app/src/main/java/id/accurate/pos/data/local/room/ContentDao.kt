package id.accurate.pos.data.local.room

import androidx.lifecycle.LiveData
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
    fun getCities() : LiveData<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities : List<CityEntity>)

    @Query("SELECT * FROM `user-entity` ORDER BY name COLLATE NOCASE")
    fun sortByName() : DataSource.Factory<Int, UserEntity>

    @Query("SELECT * FROM `user-entity` WHERE city LIKE :city")
    fun sortByCity(city : String) : DataSource.Factory<Int, UserEntity>

    @Query("SELECT * FROM `user-entity` WHERE name LIKE '%' || :name || '%'")
    fun searchByName(name : String) : DataSource.Factory<Int, UserEntity>

}