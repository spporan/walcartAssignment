package poran.cse.walcartassignment.data.dto


import com.squareup.moshi.Json

data class CategoriesResponse(
    @Json(name = "data")
    val `data`: Data?
)