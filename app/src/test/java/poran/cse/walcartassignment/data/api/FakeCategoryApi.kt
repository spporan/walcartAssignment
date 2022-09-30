package poran.cse.walcartassignment.data.api

import okhttp3.RequestBody
import poran.cse.walcartassignment.data.dto.CategoriesResponse
import poran.cse.walcartassignment.data.dto.Data
import poran.cse.walcartassignment.data.dto.GetCategories
import poran.cse.walcartassignment.data.dto.Result
import poran.cse.walcartassignment.domain.model.Category
import retrofit2.Response

class FakeCategoryApi(val categoriesResponse: CategoriesResponse): CategoriesApi {
    override suspend fun getCategories(body: RequestBody): Response<CategoriesResponse> {
        return Response.success(categoriesResponse)
    }
}