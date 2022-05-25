package id.accurate.pos.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.accurate.pos.data.local.entity.CityEntity
import id.accurate.pos.data.local.entity.UserEntity
import id.accurate.pos.vo.Resource

interface ContentDataSource {

    fun getUsers() : LiveData<Resource<PagedList<UserEntity>>>
    fun getCities() : LiveData<Resource<PagedList<CityEntity>>>

}