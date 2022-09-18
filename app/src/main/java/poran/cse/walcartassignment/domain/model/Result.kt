package poran.cse.walcartassignment.domain.model


import com.squareup.moshi.Json

data class Result(
    @Json(name = "categories")
    val categories: List<Category>?
)