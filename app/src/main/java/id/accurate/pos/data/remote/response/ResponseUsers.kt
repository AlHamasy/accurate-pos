package id.accurate.pos.data.remote.response

import com.google.gson.annotations.SerializedName

//data class ResponseUsers(
//
//	@field:SerializedName("ResponseUsers")
//	val responseUsers: List<ResponseUsersItem?>? = null
//)

data class ResponseUsersItem(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
