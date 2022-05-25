package id.accurate.pos.data.local

import androidx.paging.DataSource
import id.accurate.pos.data.local.entity.CityEntity
import id.accurate.pos.data.local.entity.UserEntity
import id.accurate.pos.data.local.room.ContentDao

class LocalDataSource private constructor(private val contentDao: ContentDao){

    companion object{

        private var INSTANCE : LocalDataSource? = null

        fun getInstance(contentDao : ContentDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(contentDao)

    }

    fun getUsers() : DataSource.Factory<Int, UserEntity> = contentDao.getUsers()

    fun getCities() : DataSource.Factory<Int, CityEntity> = contentDao.getCities()

    fun insertUsers(users : List<UserEntity>) = contentDao.insertUsers(users)

    fun insertCities(cities : List<CityEntity>) = contentDao.insertCities(cities)

}