package poran.cse.walcartassignment.domain.useCase

import poran.cse.walcartassignment.domain.repository.CategoryRepository

class GetCategoriesFromLocal(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(uid: String) = categoryRepository.getCategoryFromDB(uid)
}