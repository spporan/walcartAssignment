package poran.cse.walcartassignment.domain.model


import com.squareup.moshi.Json

data class Data(
    @Json(name = "getCategories")
    val getCategories: GetCategories?
)