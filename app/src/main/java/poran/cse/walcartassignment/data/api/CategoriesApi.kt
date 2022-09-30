package poran.cse.walcartassignment.data.api

import okhttp3.RequestBody
import poran.cse.walcartassignment.data.dto.CategoriesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CategoriesApi {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Host: devapiv2.walcart.com",
    )
    @POST("graphql")
    suspend fun getCategories(@Body body: RequestBody): Response<CategoriesResponse>
}