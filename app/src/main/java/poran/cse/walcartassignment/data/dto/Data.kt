package poran.cse.walcartassignment.data.dto


import com.squareup.moshi.Json

data class Data(
    @Json(name = "getCategories")
    val getCategories: GetCategories?
)