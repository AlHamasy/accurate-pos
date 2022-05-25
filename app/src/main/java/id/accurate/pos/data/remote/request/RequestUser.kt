package id.accurate.pos.data.remote.request

import com.google.gson.annotations.SerializedName

data class RequestUser(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
