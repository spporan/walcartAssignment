package poran.cse.walcartassignment.data.dto


import com.squareup.moshi.Json
import poran.cse.walcartassignment.domain.model.Category

data class Result(
    @Json(name = "categories")
    val categories: List<Category>?
)