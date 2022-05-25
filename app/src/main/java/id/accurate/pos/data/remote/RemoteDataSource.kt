package id.accurate.pos.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.accurate.pos.data.remote.response.ResponseCitiesItem
import id.accurate.pos.data.remote.response.ResponseUsersItem
import id.accurate.pos.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply {
                    instance = this
                }
            }
    }

    fun getUsers() : LiveData<ApiResponse<List<ResponseUsersItem>>>{

        val resultUsers = MutableLiveData<ApiResponse<List<ResponseUsersItem>>>()
        ApiClient.getService().getUsers().enqueue(object : Callback<List<ResponseUsersItem>> {
            override fun onResponse(call: Call<List<ResponseUsersItem>>, response: Response<List<ResponseUsersItem>>) {
                response.body()?.let {
                    resultUsers.value = ApiResponse.success(it)
                }
            }
            override fun onFailure(call: Call<List<ResponseUsersItem>>, t: Throwable) {
            }
        })
        return resultUsers
    }

    fun getCities() : LiveData<ApiResponse<List<ResponseCitiesItem>>>{

        val resultCities = MutableLiveData<ApiResponse<List<ResponseCitiesItem>>>()
        ApiClient.getService().getCities().enqueue(object : Callback<List<ResponseCitiesItem>> {
            override fun onResponse(call: Call<List<ResponseCitiesItem>>, response: Response<List<ResponseCitiesItem>>) {
                response.body()?.let {
                    resultCities.value = ApiResponse.success(it)
                }
            }
            override fun onFailure(call: Call<List<ResponseCitiesItem>>, t: Throwable) {
            }
        })
        return resultCities
    }

}