package poran.cse.walcartassignment.data.dto


import com.squareup.moshi.Json

data class GetCategories(
    @Json(name = "result")
    val result: Result?
)