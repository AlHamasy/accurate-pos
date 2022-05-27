package id.accurate.pos.network

import id.accurate.pos.data.remote.request.RequestUser
import id.accurate.pos.data.remote.response.ResponseCitiesItem
import id.accurate.pos.data.remote.response.ResponseUsersItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("accurate/user")
    fun getUsers() : Call<List<ResponseUsersItem>>

    @POST("accurate/user")
    fun insertUser(@Body requestUser: RequestUser) : Call<List<ResponseUsersItem>>

    @GET("accurate/city")
    fun getCities() : Call<List<ResponseCitiesItem>>

}