package id.accurate.pos.data.remote.response

import com.google.gson.annotations.SerializedName

//data class ResponseCities(
//
//	@field:SerializedName("ResponseCities")
//	val responseCities: List<ResponseCitiesItem?>? = null
//)

data class ResponseCitiesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
