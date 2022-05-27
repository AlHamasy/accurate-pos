package id.accurate.pos.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.accurate.pos.data.local.LocalDataSource
import id.accurate.pos.data.local.entity.CityEntity
import id.accurate.pos.data.local.entity.UserEntity
import id.accurate.pos.data.remote.ApiResponse
import id.accurate.pos.data.remote.RemoteDataSource
import id.accurate.pos.data.remote.request.RequestUser
import id.accurate.pos.data.remote.response.ResponseCitiesItem
import id.accurate.pos.data.remote.response.ResponseUsersItem
import id.accurate.pos.utils.AppExecutors
import id.accurate.pos.vo.Resource

class ContentRepository private constructor(private val remoteDataSource : RemoteDataSource,
                                            private val localDataSource: LocalDataSource,
                                            private val appExecutors: AppExecutors) : ContentDataSource{

    companion object{
        @Volatile
        private var instance : ContentRepository? = null
        fun getInstance(remoteData : RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors) : ContentRepository =
            instance ?: synchronized(this){
                instance ?: ContentRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getUsers(): LiveData<Resource<PagedList<UserEntity>>> {
        return object : NetworkBoundResource<PagedList<UserEntity>, List<ResponseUsersItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<UserEntity>> {
                val config = PagedList.Config.Builder()
                                                .setEnablePlaceholders(false)
                                                .setInitialLoadSizeHint(6)
                                                .setPageSize(6)
                                                .build()
                return LivePagedListBuilder(localDataSource.getUsers(), config).build()
            }
            override fun shouldFetch(data: PagedList<UserEntity>?): Boolean {
                //return data == null || data.isEmpty()
                return true
            }
            override fun createCall(): LiveData<ApiResponse<List<ResponseUsersItem>>> {
                return remoteDataSource.getUsers()
            }
            override fun saveCallResult(data: List<ResponseUsersItem>) {
                val userList = ArrayList<UserEntity>()
                for (user in data){
                    val userEntity = UserEntity(user.id ?: "",
                                                user.name ?: "",
                                            user.address ?:"",
                                        user.phoneNumber ?: "",
                                                user.city ?: "",
                                                user.email ?: "")

                    userList.add(userEntity)
                }
                localDataSource.insertUsers(userList)
            }
        }.asLiveData()
    }

    override fun getCities(): LiveData<Resource<List<CityEntity>>> {
        return object : NetworkBoundResource<List<CityEntity>, List<ResponseCitiesItem>>(appExecutors){
            override fun loadFromDB(): LiveData<List<CityEntity>> {
                return localDataSource.getCities()
            }
            override fun shouldFetch(data: List<CityEntity>?): Boolean {
                return data == null || data.isEmpty()
            }
            override fun createCall(): LiveData<ApiResponse<List<ResponseCitiesItem>>> {
                return remoteDataSource.getCities()
            }
            override fun saveCallResult(data: List<ResponseCitiesItem>) {
                val cityList = ArrayList<CityEntity>()
                for (city in data){
                    val cityEntity = CityEntity(city.id ?: "",
                                             city.name ?: "")
                    cityList.add(cityEntity)
                }
                localDataSource.insertCities(cityList)
            }
        }.asLiveData()
    }

    override fun sortByName(): LiveData<Resource<PagedList<UserEntity>>> {
        return object : NetworkBoundResource<PagedList<UserEntity>, List<ResponseUsersItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<UserEntity>> {
                val config = PagedList.Config.Builder()
                                                .setEnablePlaceholders(false)
                                                .setInitialLoadSizeHint(6)
                                                .setPageSize(6)
                                                .build()
                return LivePagedListBuilder(localDataSource.sortByName(), config).build()
            }
            override fun shouldFetch(data: PagedList<UserEntity>?): Boolean {
                return data == null || data.isEmpty()
            }
            override fun createCall(): LiveData<ApiResponse<List<ResponseUsersItem>>> {
                return remoteDataSource.getUsers()
            }
            override fun saveCallResult(data: List<ResponseUsersItem>) {
            }
        }.asLiveData()
    }

    override fun sortByCity(city : String): LiveData<Resource<PagedList<UserEntity>>> {

        return object : NetworkBoundResource<PagedList<UserEntity>, List<ResponseUsersItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<UserEntity>> {
                val config = PagedList.Config.Builder()
                                                .setEnablePlaceholders(false)
                                                .setInitialLoadSizeHint(6)
                                                .setPageSize(6)
                                                .build()
                return LivePagedListBuilder(localDataSource.sortByCity(city), config).build()
            }
            override fun shouldFetch(data: PagedList<UserEntity>?): Boolean {
                return data == null || data.isEmpty()
            }
            override fun createCall(): LiveData<ApiResponse<List<ResponseUsersItem>>> {
                return remoteDataSource.getUsers()
            }
            override fun saveCallResult(data: List<ResponseUsersItem>) {
            }
        }.asLiveData()
    }

    override fun searchByName(name : String): LiveData<Resource<PagedList<UserEntity>>> {
        return object : NetworkBoundResource<PagedList<UserEntity>, List<ResponseUsersItem>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<UserEntity>> {
                val config = PagedList.Config.Builder()
                                                .setEnablePlaceholders(false)
                                                .setInitialLoadSizeHint(6)
                                                .setPageSize(6)
                                                .build()
                return LivePagedListBuilder(localDataSource.searchByName(name), config).build()
            }
            override fun shouldFetch(data: PagedList<UserEntity>?): Boolean {
                return data == null || data.isEmpty()
            }
            override fun createCall(): LiveData<ApiResponse<List<ResponseUsersItem>>> {
                return remoteDataSource.getUsers()
            }
            override fun saveCallResult(data: List<ResponseUsersItem>) {
            }
        }.asLiveData()
    }

    override fun insertUser(requestUser: RequestUser): LiveData<String> {
        return remoteDataSource.insertUser(requestUser)
    }


}