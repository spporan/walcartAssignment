package poran.cse.walcartassignment.domain.model


import com.squareup.moshi.Json

data class GetCategories(
    @Json(name = "result")
    val result: Result?
)