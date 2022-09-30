package poran.cse.walcartassignment.data

import poran.cse.walcartassignment.data.dto.CategoriesResponse
import poran.cse.walcartassignment.data.dto.Data
import poran.cse.walcartassignment.data.dto.GetCategories
import poran.cse.walcartassignment.data.dto.Result
import poran.cse.walcartassignment.domain.model.Category

fun mockCategories(): List<Category> {
    return listOf(
        Category("uid1", "attr",
            "categoryNameBn", "2345123456",
            "categoryNameEn", image = null, null,
            null, null, null, null),
        Category("uid2", "attr",
            "categoryNameBn", "2345123456",
            "categoryNameEn", image = null, null,
            null, null, null, null),
        Category("uid3", "attr",
            "categoryNameBn", "2345123456",
            "categoryNameEn", image = null, null,
            null, null, null, null)
    )
}

fun createCategoryResponse(): CategoriesResponse {
    return CategoriesResponse(
        data = Data(
            getCategories = GetCategories(
                result = Result(
                    categories = mockCategories()
                )
            )
        )
    )
}