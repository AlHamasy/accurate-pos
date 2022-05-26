package id.accurate.pos.ui.main

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.accurate.pos.data.local.entity.UserEntity
import id.accurate.pos.repository.ContentRepository
import id.accurate.pos.vo.Resource

class MainViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getUsers() : LiveData<Resource<PagedList<UserEntity>>> = contentRepository.getUsers()

    fun sortByName() : LiveData<Resource<PagedList<UserEntity>>> = contentRepository.sortByName()

    fun sortByCity(city : String) : LiveData<Resource<PagedList<UserEntity>>> = contentRepository.sortByCity(city)

    fun searchByName(name : String) : LiveData<Resource<PagedList<UserEntity>>> = contentRepository.searchByName(name)

}